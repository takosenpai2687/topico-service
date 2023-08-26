package com.topico.entity;

import com.topico.enums.LikeStatus;
import jakarta.persistence.*;
import lombok.*;

@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserLikePost extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private LikeStatus likeStatus;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;
}
