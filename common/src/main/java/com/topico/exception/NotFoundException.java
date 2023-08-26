package com.topico.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

public class NotFoundException extends ApiException {

    private static final int NOT_FOUND = HttpStatus.NOT_FOUND.value();

    public NotFoundException(String message) {
        super(NOT_FOUND, message);
    }

    public NotFoundException() {
        super(NOT_FOUND, "not found");
    }
}
