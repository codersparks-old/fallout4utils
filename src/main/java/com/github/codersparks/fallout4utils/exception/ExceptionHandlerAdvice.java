package com.github.codersparks.fallout4utils.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Created by codersparks on 15/11/2015.
 */
@ControllerAdvice
public class ExceptionHandlerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {HackingSessionAlreadyExistsException.class})
    public ResponseEntity<Object> handleSessionAlreadyExistsException(HackingSessionAlreadyExistsException ex, WebRequest request) {
        String body = "HackingSession already exists, check logs for stacktrace";
        return handleExceptionInternal(ex, body, new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    @ExceptionHandler(value={HackingSessionNotFoundException.class})
    public ResponseEntity<Object> handleSessionNotFoundException(HackingSessionNotFoundException ex, WebRequest request) {
        String body = "HackingSession already exists, check logs for stacktrace";
        return handleExceptionInternal(ex, body, new HttpHeaders(), HttpStatus.CONFLICT, request);
    }
}
