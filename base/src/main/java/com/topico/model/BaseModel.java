package com.topico.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
public abstract class BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;


    @Column(name = "ctime", nullable = false, updatable = false)
    protected LocalDateTime ctime = LocalDateTime.now();

    @Column(name = "utime", nullable = false)
    protected LocalDateTime updateTime = LocalDateTime.now();


}
