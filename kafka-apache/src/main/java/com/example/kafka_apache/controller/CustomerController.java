package com.example.kafka_apache.controller;

import com.example.kafka_apache.model.Customer;
import com.example.kafka_apache.service.CustomerService;
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

    @PostMapping("")
    public Mono<Customer> create(@RequestBody Customer customer)
    {
        return customerService.save(customer)
                .flatMap(this::notifyCustomerCreated);
    }

    @GetMapping("/{id}")
    public Mono<Customer> getById(@PathVariable String id)
    {
        return customerService.findById(id);
    }

    @GetMapping("/all")
    public Flux<Customer> getAll()
    {
        return customerService.findAll();
    }

    private Mono<Customer> notifyCustomerCreated(Customer customer)
    {
        return Mono.fromFuture(
                kafkaTemplate.send(
                        "test-topic",
                        "customer-created",
                        String.format("customer has been created [id = %s]", customer.getId()))
        ).map(x -> customer);
    }
}
