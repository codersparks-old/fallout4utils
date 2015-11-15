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

    @Override
    public String toString() {
        return "Session{" +
                "id='" + id + '\'' +
                ", candidates=" + candidates +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Session session = (Session) o;

        if (!getId().equals(session.getId())) return false;
        return !(getCandidates() != null ? !getCandidates().equals(session.getCandidates()) : session.getCandidates() != null);

    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + (getCandidates() != null ? getCandidates().hashCode() : 0);
        return result;
    }
}
