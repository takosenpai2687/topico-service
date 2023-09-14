package usyd.elec5619.topicoservice.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class Comment {
    private Long id;
    private Long postId;
    private Long authorId;
    private Long parentId;
    private Long replyToUserId;
    private String content;
    private Integer likes;
    private Integer dislikes;
    private Integer replies;
    private LocalDateTime ctime;
    private LocalDateTime utime;
}