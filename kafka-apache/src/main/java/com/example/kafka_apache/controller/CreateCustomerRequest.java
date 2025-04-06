package com.example.kafka_apache.controller;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateCustomerRequest {

    @NotNull
    @NotBlank
    @Size(min = 10, max = 100)
    public String name;

    @NotNull
    @NotBlank
    @Email
    public String email;
}
