package usyd.elec5619.topicoservice.exception.http;

import org.springframework.http.HttpStatus;
import usyd.elec5619.topicoservice.exception.ApiException;

public class BadRequestException extends ApiException {
    public BadRequestException(String message) {
        super(HttpStatus.BAD_REQUEST.value(), message);
    }

    public BadRequestException() {
        super(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase());
    }
}
