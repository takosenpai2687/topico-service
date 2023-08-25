package com.topico.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@MappedSuperclass
@NoArgsConstructor
@Entity
public class Role extends BaseEntity {
    @NotBlank
    @Column(nullable = false, unique = true)
    String name;

    @OneToMany(mappedBy = "role")
    private List<UserCommunityRole> userCommunityRoles = new ArrayList<>();

    @OneToMany
    @JoinTable(
            name = "role_permission",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "permission_id")
    )
    private List<Permission> permissions = new ArrayList<>();
}
