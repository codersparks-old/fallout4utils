package com.github.codersparks.fallout4utils.controllers;

import com.github.codersparks.fallout4utils.data.HackingSession;
import com.github.codersparks.fallout4utils.exception.HackingSessionException;
import com.github.codersparks.fallout4utils.interfaces.HackingSessionService;
import org.springframework.beans.factory.annotation.Autowired;
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
public class HackingSessionController {

    private final HackingSessionService hackingSessionService;

    @Autowired
    public HackingSessionController(HackingSessionService hackingSessionService) {
        this.hackingSessionService = hackingSessionService;
    }

    @RequestMapping(
            value = "/",
            method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, MediaType.TEXT_XML_VALUE}
    )
    public ResponseEntity<HackingSession> createSession() throws HackingSessionException {
        HackingSession hackingSession = hackingSessionService.createHackingSession();
        return new ResponseEntity<HackingSession>(hackingSession, HttpStatus.CREATED);
    }

    @RequestMapping(
            value = "/",
            method = RequestMethod.PUT,
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, MediaType.TEXT_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, MediaType.TEXT_XML_VALUE}
    )
    public ResponseEntity<HackingSession> updateSession(@RequestBody HackingSession hackingSession) throws HackingSessionException {
        HackingSession updatedHackingSession = hackingSessionService.updateHackingSession(hackingSession);

        return new ResponseEntity<HackingSession>(updatedHackingSession, HttpStatus.OK);
    }
}
