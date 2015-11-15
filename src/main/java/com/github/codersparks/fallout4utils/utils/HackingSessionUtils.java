package com.github.codersparks.fallout4utils.utils;

import com.github.codersparks.fallout4utils.data.HackingSession;

import java.util.List;

/**
 * Created by codersparks on 14/11/2015.
 */
public class HackingSessionUtils {

    public HackingSession createSession() {
        return new HackingSession();
    }

    public HackingSession createSession(String id) {
        return new HackingSession(id);
    }
}
