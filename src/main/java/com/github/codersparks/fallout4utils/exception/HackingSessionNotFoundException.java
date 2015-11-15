package com.github.codersparks.fallout4utils.exception;

/**
 * Created by codersparks on 15/11/2015.
 */
public class HackingSessionNotFoundException extends HackingSessionException {

    public HackingSessionNotFoundException(String message) {
        super(message);
    }

    public HackingSessionNotFoundException(String message, Throwable throwable) {
        super(message,throwable);
    }
}
