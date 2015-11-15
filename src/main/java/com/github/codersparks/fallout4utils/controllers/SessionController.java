package com.github.codersparks.fallout4utils.controllers;

import com.github.codersparks.fallout4utils.data.Session;
import com.github.codersparks.fallout4utils.exception.SessionException;
import com.github.codersparks.fallout4utils.interfaces.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by codersparks on 14/11/2015.
 */
@RestController
@RequestMapping(value = "/api/session")
public class SessionController {

    private final SessionService sessionService;

    @Autowired
    public SessionController(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @RequestMapping(
            value = "/",
            method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, MediaType.TEXT_XML_VALUE}
    )
    public ResponseEntity<Session> createSession() throws SessionException {
        Session session = sessionService.createSession();
        return new ResponseEntity<Session>(session, HttpStatus.CREATED);
    }

    @RequestMapping(
            value = "/",
            method = RequestMethod.PUT,
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, MediaType.TEXT_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, MediaType.TEXT_XML_VALUE}
    )
    public ResponseEntity<Session> updateSession(@RequestBody Session session) throws SessionException {
        Session updatedSession = sessionService.updateSession(session);

        return new ResponseEntity<Session>(updatedSession, HttpStatus.OK);
    }
}
