package com.github.codersparks.fallout4utils.utils;

import com.github.codersparks.fallout4utils.data.Session;

import java.util.List;

/**
 * Created by codersparks on 14/11/2015.
 */
public class SessionUtils {

    public Session createSession() {
        return new Session();
    }

    public Session createSession(String id, List<String> wordCollection) {
        return new Session(id ,wordCollection);
    }
}
