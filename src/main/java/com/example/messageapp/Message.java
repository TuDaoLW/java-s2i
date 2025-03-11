package com.example.messageapp;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Data
@Document(indexName = "messages")
public class Message {
    @Id
    private String id;
    private String content;

    public Message() {}
    public Message(String id, String content) {
        this.id = id;
        this.content = content;
    }
}