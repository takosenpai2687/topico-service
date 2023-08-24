package com.topico.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Response<T> {
    private int code;
    private String message;
    private T data;

    public Response(int code, String message) {
        this.code = code;
        this.message = message;
    }

    // SUCCESS

    public static <T> Response<T> success(T data) {
        return Response.<T>builder().code(HttpStatus.OK.value()).message("success").data(data).build();
    }

    public static <T> Response<T> success() {
        return Response.<T>builder().code(HttpStatus.OK.value()).message("success").build();
    }

    // BAD REQUEST

    // FORBIDDEN

    // NOT FOUND

    // INTERNAL ERROR
}
