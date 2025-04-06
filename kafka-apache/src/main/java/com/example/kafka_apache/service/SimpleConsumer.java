package com.example.kafka_apache.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class SimpleConsumer {

    final static Logger LOGGER = LoggerFactory.getLogger(SimpleConsumer.class);

    @KafkaListener(
            id = "hai-mae",
            topics = "test-topic",
            autoStartup = "true",
            concurrency = "1")
    public void listen(String msg) {
        LOGGER.info("Get message : {}", msg);
    }
}
