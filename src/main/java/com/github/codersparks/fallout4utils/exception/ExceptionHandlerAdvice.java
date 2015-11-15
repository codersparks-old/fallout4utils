package com.github.codersparks.fallout4utils.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger logger = LoggerFactory.getLogger(ExceptionHandlerAdvice.class);

    @ExceptionHandler(value = {HackingSessionAlreadyExistsException.class})
    public ResponseEntity<ExceptionResponse> handleSessionAlreadyExistsException(HackingSessionAlreadyExistsException ex, WebRequest request) {
        String body = "HackingSession already exists, check logs for stacktrace";
        logger.error("HackingSessionAlreadyExistsException caught", ex);
        ExceptionResponse response = new ExceptionResponse();
        response.setMessage(ex.getLocalizedMessage());
        if(ex.getCause() != null) {
            response.setCausedBy(ex.getCause().getClass().getCanonicalName());
        }
        response.setExceptionName(ex.getClass().getCanonicalName());

        return new ResponseEntity<ExceptionResponse>(response, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value={HackingSessionNotFoundException.class})
    public ResponseEntity<ExceptionResponse> handleSessionNotFoundException(HackingSessionNotFoundException ex, WebRequest request) {
        String body = "HackingSession already exists, check logs for stacktrace";
        logger.error("HackingSessionNotFoundException caught", ex);
        ExceptionResponse response = new ExceptionResponse();
        response.setMessage(ex.getLocalizedMessage());
        if(ex.getCause() != null) {
            response.setCausedBy(ex.getCause().getClass().getCanonicalName());
        }
        response.setExceptionName(ex.getClass().getCanonicalName());

        return new ResponseEntity<ExceptionResponse>(response, HttpStatus.NOT_FOUND);

    }
}
