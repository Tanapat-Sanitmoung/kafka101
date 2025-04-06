package com.example.kafka_apache.mapper;

import com.example.kafka_apache.controller.CreateCustomerRequest;
import com.example.kafka_apache.model.Customer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = CustomerMapper.class)
public interface CustomerMapper {

    Customer toCustomer(CreateCustomerRequest request);
}
