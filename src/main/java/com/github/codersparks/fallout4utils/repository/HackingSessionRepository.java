package com.github.codersparks.fallout4utils.repository;

import com.github.codersparks.fallout4utils.data.HackingSession;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by codersparks on 14/11/2015.
 */
public interface HackingSessionRepository extends MongoRepository<HackingSession, String> {
}
