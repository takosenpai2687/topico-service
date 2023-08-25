package com.topico.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Permission extends BaseEntity {
    @NotBlank
    @Column(nullable = false, unique = true)
    String name;

}
