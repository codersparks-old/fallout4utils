package com.github.codersparks.fallout4utils.serivce;

import com.github.codersparks.fallout4utils.data.Session;
import com.github.codersparks.fallout4utils.exception.SessionAlreadyExistsException;
import com.github.codersparks.fallout4utils.exception.SessionException;
import com.github.codersparks.fallout4utils.interfaces.SessionService;
import com.github.codersparks.fallout4utils.repository.SessionRepository;
import com.github.codersparks.fallout4utils.utils.SessionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by codersparks on 14/11/2015.
 */
public class SessionServiceImpl implements SessionService{

    private final static Logger logger = LoggerFactory.getLogger(SessionServiceImpl.class);

    private final SessionRepository repository;
    private final SessionUtils sessionUtils;

    @Autowired
    public SessionServiceImpl(SessionRepository repository, SessionUtils sessionUtils) {
        this.repository = repository;
        this.sessionUtils = sessionUtils;
    }

    @Override
    public Session createSession() throws SessionException {

        Session session = sessionUtils.createSession();
        logger.info("Created session with id: " + session.getId());

        // Unlikely to hit but just incase
        Session temp = repository.findOne(session.getId());
        if(temp != null) {
            throw new SessionAlreadyExistsException("Session with id: " + session.getId() + " already exists");
        }

        // We now store it into the repository for future use
        repository.save(session);

        return session;
    }
}
