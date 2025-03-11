package com.example.messageapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/messages")
public class MessageController {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private MessageService messageService;

    @Value("${app.kafka.topic}")
    private String topic;

    @PostMapping
    public String sendMessage(@RequestBody Message message) {
        kafkaTemplate.send(topic, message.getId(), message.getContent());
        return "Message sent to Kafka: " + message.getContent();
    }

    @GetMapping("/{id}")
    public Message getMessage(@PathVariable String id) {
        return messageService.getMessage(id);
    }
}