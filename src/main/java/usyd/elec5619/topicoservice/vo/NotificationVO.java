package usyd.elec5619.topicoservice.vo;

import lombok.Builder;
import lombok.Data;
import usyd.elec5619.topicoservice.model.User;
import usyd.elec5619.topicoservice.type.NotificationType;

@Data
@Builder
public class NotificationVO {
    private Long id;
    private NotificationType type;
    private User sender;
    private User receiver;
    private PostVO post;
    private CommentVO comment;
    private CommentVO replyToComment;
    private Integer total;
    private String content;
    private Boolean unread;
    private String ctime;
    private String utime;
}
