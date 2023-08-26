package com.topico.exception;

import org.springframework.http.HttpStatus;

public class ForbiddenException extends ApiException {
    private static final int FORBIDDEN = HttpStatus.FORBIDDEN.value();

    public ForbiddenException(String message) {
        super(FORBIDDEN, message);
    }

    public ForbiddenException() {
        super(FORBIDDEN, "forbidden");
    }
}
