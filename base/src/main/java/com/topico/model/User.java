package com.topico.model;


import com.topico.enums.Gender;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class User extends BaseModel {

    @NotBlank
    @Column(unique = true, nullable = false)
    private String email;

    @NotBlank
    @Column(name = "nick_name", nullable = false, unique = true)
    private String nickName;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String salt;

    @Column(nullable = false)
    private Gender gender;


    @Column(nullable = true)
    private String location;

    @Column(nullable = true)
    private String avatar;

    @Column(nullable = true)
    private String description;

    @Column(nullable = false)
    private Boolean deleted;

}
