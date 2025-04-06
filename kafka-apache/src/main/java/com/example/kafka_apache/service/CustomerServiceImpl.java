package com.example.kafka_apache.service;


import com.example.kafka_apache.model.Customer;
import com.example.kafka_apache.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService
{
    private final CustomerRepository repository;

    @Override
    public Mono<Customer> findById(String id) {
        return repository.findById(id);
    }

    @Override
    public Mono<Customer> findByEmail(String email) {
        return repository.findByEmail(email);
    }

    @Override
    public Flux<Customer> findAll() {
        return repository.findAll();
    }

    @Override
    public Mono<Customer> save(Customer customer) {
        customer.setId(UUID.randomUUID().toString());
        return repository.save(customer);
    }

    @Override
    public Mono<Void> deleteById(String id) {
        return repository.deleteById(id);
    }
}