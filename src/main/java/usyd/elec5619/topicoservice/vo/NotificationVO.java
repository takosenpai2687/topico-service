package usyd.elec5619.topicoservice.vo;

import lombok.Builder;
import lombok.Data;
import usyd.elec5619.topicoservice.model.Comment;
import usyd.elec5619.topicoservice.model.Post;
import usyd.elec5619.topicoservice.model.User;
import usyd.elec5619.topicoservice.type.NotificationType;

import java.time.LocalDateTime;

@Data
@Builder
public class NotificationVO {
    private Long id;
    private NotificationType type;
    private User sender;
    private User receiver;
    private Post post;
    private Comment comment;
    private Comment replyToComment;
    private Integer total;
    private String content;
    private Boolean unread;
    private LocalDateTime ctime;
    private LocalDateTime utime;
}
