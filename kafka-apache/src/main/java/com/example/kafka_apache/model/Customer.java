package com.example.kafka_apache.model;

import lombok.*;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table
@Builder
public class Customer {
    @PrimaryKey
    public String id;
    public String name;
    public String email;
}
