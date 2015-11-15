package com.github.codersparks.fallout4utils.serivce;

import com.github.codersparks.fallout4utils.data.HackingSession;
import com.github.codersparks.fallout4utils.exception.HackingSessionAlreadyExistsException;
import com.github.codersparks.fallout4utils.exception.HackingSessionException;
import com.github.codersparks.fallout4utils.exception.HackingSessionNotFoundException;
import com.github.codersparks.fallout4utils.interfaces.HackingSessionService;
import com.github.codersparks.fallout4utils.repository.HackingSessionRepository;
import com.github.codersparks.fallout4utils.utils.HackingSessionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by codersparks on 14/11/2015.
 */
public class HackingSessionServiceImpl implements HackingSessionService {

    private final static Logger logger = LoggerFactory.getLogger(HackingSessionServiceImpl.class);

    private final HackingSessionRepository repository;
    private final HackingSessionUtils hackingSessionUtils;

    @Autowired
    public HackingSessionServiceImpl(HackingSessionRepository repository, HackingSessionUtils hackingSessionUtils) {
        this.repository = repository;
        this.hackingSessionUtils = hackingSessionUtils;
    }

    @Override
    public HackingSession createHackingSession() throws HackingSessionException {

        HackingSession hackingSession = hackingSessionUtils.createSession();
        logger.info("Created hackingSession with id: " + hackingSession.getId());

        // Unlikely to hit but just incase
        if(repository.exists(hackingSession.getId())) {
            throw new HackingSessionAlreadyExistsException("HackingSession with id: " + hackingSession.getId() + " already exists");
        }

        // We now store it into the repository for future use
        HackingSession storedHackingSession = repository.save(hackingSession);

        return storedHackingSession;
    }

    @Override
    public HackingSession updateHackingSession(HackingSession hackingSession) throws HackingSessionException {

        logger.info("Attempting to update hackingSession: " + hackingSession);

        // We want to check that it already exists
        if( ! repository.exists(hackingSession.getId())) {
            throw new HackingSessionNotFoundException("Cannot find hackingSession with id: " + hackingSession.getId());
        }

        // We are now updating the repository
        HackingSession storedHackingSession = repository.save(hackingSession);
        return storedHackingSession;


    }
}
