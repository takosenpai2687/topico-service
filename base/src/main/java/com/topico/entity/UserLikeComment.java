package com.topico.entity;

import com.topico.enums.LikeStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@MappedSuperclass
@NoArgsConstructor
@Entity
public class UserLikeComment extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)
    private LikeStatus likeStatus;

    @ManyToOne
    @JoinColumn(name = "comment_id")
    private Comment comment;
}
