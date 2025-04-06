package com.example.kafka_apache.repository;

import com.example.kafka_apache.model.Customer;
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface CustomerRepository extends ReactiveCassandraRepository<Customer, String> {
    Mono<Customer> findByEmail(String email);
}
