package com.github.codersparks.fallout4utils.data;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Document
public class Session {

    public Session(String id, List<String> candidates) {
        this.id = id;
        this.candidates = candidates;
    }

    public Session() {
        this.id = UUID.randomUUID().toString();
        this.candidates = new ArrayList<>();
    }

    @Id
    private String id;

    List<String> candidates;

    public List<String> getCandidates() {
        return candidates;
    }

    public void setCandidates(List<String> candidates) {
        this.candidates = candidates;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
