package com.example.kafka_apache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SimpleController {

    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;

    private final static String TOPIC = "test-topic";

    @PostMapping("/publish")
    public void publishMessage(@RequestParam String msg)
    {
        this.kafkaTemplate.send(TOPIC, msg);
    }
}
