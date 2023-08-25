package com.topico.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@MappedSuperclass
@NoArgsConstructor
@Entity
public class Tag extends BaseEntity {

    private String name;

    @ManyToMany(mappedBy = "tags")
    private Set<Community> communities = new HashSet<>();

}
