package com.github.codersparks.fallout4utils.exception;

import javax.validation.constraints.NotNull;

/**
 * Created by codersparks on 15/11/2015.
 */
public class ExceptionResponse {

    @NotNull
    private String exceptionName;

    @NotNull
    private String message;

    private String causedBy;

    public String getExceptionName() {
        return exceptionName;
    }

    public void setExceptionName(String exceptionName) {
        this.exceptionName = exceptionName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCausedBy() {
        return causedBy;
    }

    public void setCausedBy(String causedBy) {
        this.causedBy = causedBy;
    }
}
