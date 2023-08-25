package com.topico.dto;

import com.topico.enums.Gender;
import jakarta.annotation.Nullable;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserDTO {
    private Long id;
    @Nullable
    private String nickName;
    @Nullable
    private String password;
    @Nullable
    private Gender gender;
    @Nullable
    private Integer age;
    @Nullable
    private String location;
    @Nullable
    private String avatar;
    @Nullable
    private String description;

}
