package com.example.kafka_apache.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SimpleConsumer {

    @KafkaListener(
            id = "hai-mae",
            topics = "test-topic",
            autoStartup = "true",
            concurrency = "1")
    public void listen(String msg) {
        log.info("Get message : {}", msg);
    }
}
