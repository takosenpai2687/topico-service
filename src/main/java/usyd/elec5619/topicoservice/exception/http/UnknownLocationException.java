package usyd.elec5619.topicoservice.exception.http;

import org.springframework.http.HttpStatus;
import usyd.elec5619.topicoservice.exception.ApiException;

public class UnknownLocationException extends ApiException {
    public UnknownLocationException(Integer code, String message) {
        super(code, message);
    }

    public UnknownLocationException() {
        super(HttpStatus.NOT_FOUND.value(), "Unknown Location");
    }
}
