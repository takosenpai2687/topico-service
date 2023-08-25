package com.topico.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@MappedSuperclass
@NoArgsConstructor
@Entity
public class Permission extends BaseEntity {
    @NotBlank
    @Column(nullable = false, unique = true)
    String name;

}
