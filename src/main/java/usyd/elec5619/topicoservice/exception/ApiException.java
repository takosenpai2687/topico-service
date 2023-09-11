package usyd.elec5619.topicoservice.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiException extends RuntimeException {
    private Integer code;

    public ApiException(Integer code, String message) {
        super(message);
        this.code = code;
    }
}
