package com.topico.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@Builder
public class CreateUserDto {
    @NotBlank
    private String email;
    @NotBlank
    private String password;
    @NotBlank
    private String nickName;
}
