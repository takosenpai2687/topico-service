package com.topico.entity;

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
public class Comment extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "author_id")
    private User author;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @ManyToOne
    @JoinColumn(name = "community_id")
    private Community community;

    @ManyToOne
    @JoinColumn(name = "reply_to_comment_id")
    private Comment replyToComment;

    @ManyToOne
    @JoinColumn(name = "reply_to_author_id")
    private User replyToAuthor;

    @Column(nullable = false, columnDefinition = "boolean default false")
    private Boolean unread = false;
}
