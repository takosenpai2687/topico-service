package com.topico.exception;

import com.topico.pojo.Response;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ResponseBody
    @ExceptionHandler(ApiException.class)
    public Response<?> handleApiError(ApiException ex) {
        return Response.fail(ex.getCode(), ex.getMessage());
    }

}