package com.github.codersparks.fallout4utils.exception;

/**
 * Created by codersparks on 15/11/2015.
 */
public class SessionNotFoundException extends SessionException {

    public SessionNotFoundException(String message) {
        super(message);
    }

    public SessionNotFoundException(String message, Throwable throwable) {
        super(message,throwable);
    }
}
