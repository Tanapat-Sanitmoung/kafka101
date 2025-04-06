package com.example.kafka_apache.controller;

import com.example.kafka_apache.model.Customer;
import com.example.kafka_apache.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@RequestMapping("/customers")
@RestController()
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping("")
    public Mono<Customer> create(@RequestBody Customer customer)
    {
        return customerService.save(customer);
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
}
