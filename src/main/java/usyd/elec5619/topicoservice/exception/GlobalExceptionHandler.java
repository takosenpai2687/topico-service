package usyd.elec5619.topicoservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import usyd.elec5619.topicoservice.pojo.CommonResponse;

import java.util.Objects;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ApiException.class)
    public CommonResponse<?> handleApiException(ApiException ex) {
        return CommonResponse.fail(ex);
    }

    @ExceptionHandler(RuntimeException.class)
    public CommonResponse<?> handleRuntimeException(RuntimeException ex) {
        if (ex instanceof ApiException) return handleApiException((ApiException) ex);
        Integer code = HttpStatus.INTERNAL_SERVER_ERROR.value();
        String message = Objects.requireNonNullElse(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        return CommonResponse.fail(code, message);
    }
}