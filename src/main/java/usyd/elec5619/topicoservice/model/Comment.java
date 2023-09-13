package usyd.elec5619.topicoservice.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Comment {
    private Long id;
    private Long postId;
    private Long authorId;
    private Long replyToCommentId;
    private Long replyToUserId;
    private String replyToName;
    private LocalDateTime ctime;
    private LocalDateTime utime;
    private String content;
}