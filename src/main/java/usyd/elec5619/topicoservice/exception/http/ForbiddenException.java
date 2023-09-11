package usyd.elec5619.topicoservice.exception.http;

import org.springframework.http.HttpStatus;
import usyd.elec5619.topicoservice.exception.ApiException;

public class ForbiddenException extends ApiException {
    public ForbiddenException(String message) {
        super(HttpStatus.FORBIDDEN.value(), message);
    }

    public ForbiddenException() {
        super(HttpStatus.FORBIDDEN.value(), HttpStatus.FORBIDDEN.getReasonPhrase());
    }
}
