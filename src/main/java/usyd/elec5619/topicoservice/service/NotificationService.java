package usyd.elec5619.topicoservice.service;

import org.springframework.stereotype.Service;
import usyd.elec5619.topicoservice.vo.NotificationVO;

import java.util.List;

@Service
public interface NotificationService {

    List<NotificationVO> getALlNotifications(Long receiverId);

    void readNotification(Long receiverId, Long notificationId);

    void sendLikePostNotification(Long receiverId, Long senderId, Long postId, Boolean liked);

    void sendLikeCommentNotification(Long receiverId, Long senderId, Long commentId, Boolean liked);

    void sendCommentPostNotification(Long receiverId, Long senderId, Long postId, Long commentId);

    void sendCommentReplyNotification(Long receiverId, Long senderId, Long postId, Long commentId, Long replyToCommentId);
}
