package usyd.elec5619.topicoservice.exception.http;

import org.springframework.http.HttpStatus;
import usyd.elec5619.topicoservice.exception.ApiException;

public class NotFoundException extends ApiException {
    public NotFoundException(String message) {
        super(HttpStatus.NOT_FOUND.value(), message);
    }

    public NotFoundException() {
        super(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.getReasonPhrase());
    }
}
