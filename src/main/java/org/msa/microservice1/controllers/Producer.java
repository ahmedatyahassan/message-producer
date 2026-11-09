package org.msa.microservice1.controllers;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.kafka.core.KafkaTemplate;

@RestController
public class Producer {

    // The producer and consumer use this shared Kafka topic to exchange messages.
    private static final String TOPIC = "messages";

    private final KafkaTemplate<String, String> kafkaTemplate;

    public Producer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @GetMapping("/produce/message")
    public String getMessage(@RequestParam(defaultValue = "Hello from microservice1") String message) {
        // Send the HTTP request's message to Kafka for microservice2 to receive.
        kafkaTemplate.send(TOPIC, message);
        return "Message queued: " + message;
    }

}
