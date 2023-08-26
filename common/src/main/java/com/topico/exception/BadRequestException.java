package com.topico.exception;

import lombok.*;
import org.springframework.http.HttpStatus;


@Getter
public class BadRequestException extends ApiException {

    private static final int BAD_REQUEST = HttpStatus.BAD_REQUEST.value();

    public BadRequestException(String message) {
        super(BAD_REQUEST, message);
    }

    public BadRequestException() {
        super(BAD_REQUEST, "bad request");
    }
}
