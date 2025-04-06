package com.example.kafka_apache.service;

import com.example.kafka_apache.model.Customer;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CustomerService {
    Mono<Customer> findById(String id);

    Mono<Customer> findByEmail(String email);

    Flux<Customer> findAll();

    Mono<Customer> save(Customer customer);

    Mono<Void> deleteById(String id);
}
