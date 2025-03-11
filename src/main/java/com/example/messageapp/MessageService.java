package com.example.messageapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    @Autowired
    private ElasticsearchOperations esOperations;  // Changed from ElasticsearchRestTemplate

    public void saveMessage(Message message) {
        esOperations.save(message);
    }

    public Message getMessage(String id) {
        SearchHits<Message> hits = esOperations.search(
            new CriteriaQuery(new Criteria("id").is(id)), Message.class
        );
        return hits.hasSearchHits() ? hits.getSearchHit(0).getContent() : null;
    }
}