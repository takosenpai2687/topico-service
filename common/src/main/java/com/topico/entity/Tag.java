package com.topico.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.MappedSuperclass;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Tag extends BaseEntity {

    private String name;

    @ManyToMany(mappedBy = "tags")
    private Set<Community> communities = new HashSet<>();

}
