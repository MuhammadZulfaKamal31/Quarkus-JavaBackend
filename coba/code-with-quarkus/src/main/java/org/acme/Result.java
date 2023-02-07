package org.acme;

import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;

public class Result {

    Result(String message) {
        this.succes = true;
        this.message = message;
    }

    Result(Set<? extends ConstraintViolation<?>> violations) {
        this.succes = false;
        this.message = violations.stream()
                .map(cv -> cv.getMessage())
                .collect(Collectors.joining(", "));
    }

    private String message;

    private boolean succes;

    public String getMessage() {
        return message;
    }

    public boolean getSucces() {
        return succes;
    }

}
