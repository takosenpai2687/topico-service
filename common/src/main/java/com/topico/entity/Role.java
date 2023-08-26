package com.topico.entity;

import com.topico.enums.RoleType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;
import java.util.List;

@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Role extends BaseEntity implements GrantedAuthority {
    @NotBlank
    @Column(nullable = false, unique = true)
    @Enumerated(EnumType.STRING)
    RoleType name;

    @Override
    public String getAuthority() {
        return name.toString();
    }
}
