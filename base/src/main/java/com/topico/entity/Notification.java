package com.topico.entity;

import com.topico.enums.NotificationType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.MappedSuperclass;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Notification extends BaseEntity {
    @Column(nullable = false)
    private NotificationType type;

    @Column(name = "related_id")
    private Long relatedId;

    @Column(name = "expire_time")
    private LocalDateTime expireTime;

    @Column(nullable = false, columnDefinition = "boolean default true")
    private Boolean unread = true;

}
