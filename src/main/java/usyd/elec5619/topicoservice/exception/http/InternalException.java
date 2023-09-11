package usyd.elec5619.topicoservice.exception.http;

import org.springframework.http.HttpStatus;
import usyd.elec5619.topicoservice.exception.ApiException;

public class InternalException extends ApiException {

    public InternalException(String message) {
        super(HttpStatus.INTERNAL_SERVER_ERROR.value(), message);
    }

    public InternalException() {
        super(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
    }
}
