package com.github.codersparks.fallout4utils.exception;

/**
 * Created by codersparks on 14/11/2015.
 */
public class SessionException extends Exception {

    public SessionException(String message) {
        super(message);
    }

    public SessionException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
