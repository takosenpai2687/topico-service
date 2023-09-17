package usyd.elec5619.topicoservice.model;

import lombok.Builder;
import lombok.Data;
import usyd.elec5619.topicoservice.type.NotificationType;

import java.time.LocalDateTime;

@Data
@Builder
public class Notification {
    private Long id;
    private NotificationType type;
    private Long senderId;
    private Long receiverId;
    private Long postId;
    private Long commentId;
    private Long replyToCommentId;
    private String content;
    private Boolean unread;
    private LocalDateTime ctime;
    private LocalDateTime utime;
}