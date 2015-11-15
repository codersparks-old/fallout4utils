package com.github.codersparks.fallout4utils.interfaces;

import com.github.codersparks.fallout4utils.data.HackingSession;
import com.github.codersparks.fallout4utils.exception.HackingSessionException;
import com.github.codersparks.fallout4utils.exception.HackingSessionNotFoundException;

import java.util.List;

/**
 * Created by codersparks on 14/11/2015.
 */
public interface HackingSessionService {

    HackingSession createHackingSession() throws HackingSessionException;

    HackingSession getHackingSession(String id) throws HackingSessionException;

    List<HackingSession> getAllHackingSessions() throws HackingSessionException;

    HackingSession updateHackingSession(HackingSession hackingSession) throws HackingSessionException;

    HackingSession updateCandidatesForHackingSession(List<String> candidates,  String id) throws HackingSessionNotFoundException, HackingSessionException;
}
