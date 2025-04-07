package com.example.kafka_apache.controller;

import com.example.kafka_apache.constance.KafkaTopics;
import com.example.kafka_apache.exception.DuplicationEmailException;
import com.example.kafka_apache.mapper.CustomerMapper;
import com.example.kafka_apache.model.Customer;
import com.example.kafka_apache.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@RequestMapping("/customers")
@RestController()
public class CustomerController {

    private final CustomerService customerService;
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final CustomerMapper mapper;

    @PostMapping("")
    public Mono<Customer> create(@Valid @RequestBody CreateCustomerRequest request) {
        return Mono.just(request)
                .map(mapper::toCustomer)
                .flatMap(this::saveIfNotExistsBefore)
                .flatMap(this::notifyCustomerCreated);
    }

    private Mono<Customer> saveIfNotExistsBefore(Customer newCustomer) {
        return customerService
                .findByEmail(newCustomer.getEmail())
                .flatMap(__ ->
                        Mono.error(new DuplicationEmailException(newCustomer.getEmail())).cast(Customer.class))
                .switchIfEmpty(customerService.save(newCustomer));
    }

    @GetMapping("/{id}")
    public Mono<Customer> getById(@PathVariable String id) {
        return customerService.findById(id);
    }

    @GetMapping("/all")
    public Flux<Customer> getAll() {
        return customerService.findAll();
    }

    private Mono<Customer> notifyCustomerCreated(Customer customer) {
        return Mono.fromFuture(
                        kafkaTemplate.send(
                                KafkaTopics.TEST,
                                "customer-created",
                                String.format("customer has been created [id = %s]", customer.getId()))
                )
                .map(x -> customer);
    }
}
