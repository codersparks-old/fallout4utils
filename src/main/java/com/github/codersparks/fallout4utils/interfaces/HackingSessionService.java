package com.github.codersparks.fallout4utils.interfaces;

import com.github.codersparks.fallout4utils.data.HackingSession;
import com.github.codersparks.fallout4utils.exception.HackingSessionException;

/**
 * Created by codersparks on 14/11/2015.
 */
public interface HackingSessionService {

    HackingSession createHackingSession() throws HackingSessionException;
    HackingSession updateHackingSession(HackingSession hackingSession) throws HackingSessionException;
}
