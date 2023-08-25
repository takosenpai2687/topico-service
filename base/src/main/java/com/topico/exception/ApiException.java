package com.topico.exception;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class ApiException extends RuntimeException {
    private Integer code;

    public ApiException(Integer code) {
        this.code = code;
    }

    public ApiException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }
}
