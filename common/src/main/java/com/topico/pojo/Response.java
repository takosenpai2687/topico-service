package com.topico.pojo;

import lombok.*;
import org.springframework.http.HttpStatus;

@Data
public class Response<T> {
    private int code;
    private String message;
    private T data;

    public Response(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public Response(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    // SUCCESS

    public static <T> Response<T> success(T data) {
        return new Response<T>(HttpStatus.OK.value(), "success", data);
    }


    public static <Void> Response<Void> success() {
        return new Response<>(HttpStatus.OK.value(), "success");
    }

    public static <Void> Response<Void> fail(Integer code, String message) {
        return new Response<>(code, message);
    }
}
