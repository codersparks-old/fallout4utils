package com.github.codersparks.fallout4utils.controllers;

import com.github.codersparks.fallout4utils.data.HackingSession;
import com.github.codersparks.fallout4utils.exception.HackingSessionException;
import com.github.codersparks.fallout4utils.interfaces.HackingSessionCalculationService;
import com.github.codersparks.fallout4utils.interfaces.HackingSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by codersparks on 14/11/2015.
 */
@RestController
@RequestMapping(value = "/api/session")
public class HackingSessionController {

    private final HackingSessionService hackingSessionService;
    private final HackingSessionCalculationService hackingSessionCalculationService;

    @Autowired
    public HackingSessionController(HackingSessionService hackingSessionService, HackingSessionCalculationService hackingSessionCalculationService) {
        this.hackingSessionService = hackingSessionService;
        this.hackingSessionCalculationService = hackingSessionCalculationService;
    }

    @RequestMapping(
            value = "/",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, MediaType.TEXT_XML_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, MediaType.TEXT_XML_VALUE}
    )
    public ResponseEntity<HackingSession> createSession(@RequestBody List<String> candidates) throws HackingSessionException {
        HackingSession hackingSession = hackingSessionService.createHackingSession();

        hackingSession = hackingSessionService.updateCandidatesForHackingSession(candidates, hackingSession.getId());

        return new ResponseEntity<>(hackingSession, HttpStatus.CREATED);
    }

    @RequestMapping(
            value = "/",
            method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, MediaType.TEXT_XML_VALUE}
    )
    public ResponseEntity<List<HackingSession>> getHackingSessiongs() throws HackingSessionException {

        List<HackingSession> hackingSessions = hackingSessionService.getAllHackingSessions();
        return new ResponseEntity<List<HackingSession>>(hackingSessions, HttpStatus.OK);


    }

    @RequestMapping(
            value = "/{id}",
            method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, MediaType.TEXT_XML_VALUE}
    )
    public ResponseEntity<HackingSession> getSession(@PathVariable String id) throws HackingSessionException {
        HackingSession hackingSession = hackingSessionService.getHackingSession(id);
        return new ResponseEntity<HackingSession>(hackingSession, HttpStatus.OK);
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

    @RequestMapping(
            value="/calculateDistances/{id}",
            method=RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, MediaType.TEXT_XML_VALUE}
    )
    public ResponseEntity<HackingSession> calculateDisances(@PathVariable String id) throws HackingSessionException {

        HackingSession hackingSession = hackingSessionService.getHackingSession(id);
        hackingSession = hackingSessionCalculationService.calculateLevenshteinDistances(hackingSession);
        return new ResponseEntity<HackingSession>(hackingSession, HttpStatus.OK);
    }

    @RequestMapping(
            value="/calculateBestGuess/{id}",
            method=RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, MediaType.TEXT_XML_VALUE}
    )
    public ResponseEntity<List<String>> calculateBestGuess(@PathVariable String id) throws HackingSessionException {
        HackingSession hackingSession = hackingSessionService.getHackingSession(id);
        List<String> candidateValues = hackingSessionCalculationService.calculateBestChoice(hackingSession);
        return new ResponseEntity<List<String>>(candidateValues, HttpStatus.OK);
    }

    @RequestMapping(
            value="/updateLikeness/{id}/{candidate}/{likeness}",
            method=RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, MediaType.TEXT_XML_VALUE}
    )
    public ResponseEntity<HackingSession> updateLikeness(@PathVariable String id, @PathVariable String candidate, @PathVariable int likeness) throws HackingSessionException {

        HackingSession session = hackingSessionService.getHackingSession(id);
        HackingSession updateSession = hackingSessionCalculationService.updateLikeness(session, candidate, likeness);

        return new ResponseEntity<HackingSession>(updateSession, HttpStatus.OK);
    }

    @RequestMapping(
            value="/{id}",
            method=RequestMethod.DELETE
    )
    public ResponseEntity<String> deleteSession(@PathVariable String id) throws HackingSessionException {
        HackingSession session = hackingSessionService.getHackingSession(id);
        hackingSessionService.deleteSession(session);
        return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
    }
}

