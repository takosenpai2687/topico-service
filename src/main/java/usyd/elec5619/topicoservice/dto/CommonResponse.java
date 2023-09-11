package usyd.elec5619.topicoservice.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;
import usyd.elec5619.topicoservice.exception.ApiException;

@Data
@Builder
public class CommonResponse<T> {
    private Integer code;
    private String message;
    private T data;

    public CommonResponse(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public CommonResponse(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public static <T> CommonResponse<T> success(T data) {
        return CommonResponse.<T>builder().code(HttpStatus.OK.value()).message(HttpStatus.OK.getReasonPhrase()).data(data).build();
    }

    public static <Void> CommonResponse<Void> success() {
        return CommonResponse.<Void>builder().code(HttpStatus.OK.value()).message(HttpStatus.OK.getReasonPhrase()).build();
    }

    public static <Void> CommonResponse<Void> fail(Integer code, String message) {
        return CommonResponse.<Void>builder().code(code).message(message).build();
    }

    public static <Void> CommonResponse<Void> fail(ApiException ex) {
        return CommonResponse.<Void>builder().code(ex.getCode()).message(ex.getMessage()).build();
    }

}
