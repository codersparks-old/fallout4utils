package com.github.codersparks.fallout4utils.interfaces;

import com.github.codersparks.fallout4utils.data.Session;
import com.github.codersparks.fallout4utils.exception.SessionException;

/**
 * Created by codersparks on 14/11/2015.
 */
public interface SessionService {

    Session createSession() throws SessionException;
    Session updateSession(Session session) throws SessionException;
}
