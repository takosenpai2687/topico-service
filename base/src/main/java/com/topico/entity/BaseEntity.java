package com.topico.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;


@Getter
@Setter
@AllArgsConstructor
@MappedSuperclass
@NoArgsConstructor
public abstract class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false)
    protected Long id;


    @Column(nullable = false, updatable = false)
    protected LocalDateTime ctime = LocalDateTime.now();

    @Column(nullable = false)
    protected LocalDateTime utime = LocalDateTime.now();

}
