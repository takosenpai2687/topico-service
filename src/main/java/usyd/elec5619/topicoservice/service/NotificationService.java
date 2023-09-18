package usyd.elec5619.topicoservice.service;

import org.springframework.stereotype.Service;
import usyd.elec5619.topicoservice.vo.NotificationVO;

import java.util.List;

@Service
public interface NotificationService {

    List<NotificationVO> getAllNotifications(Long receiverId);

    void readNotification(Long receiverId, Long notificationId);

    void sendLikePostNotification(Long senderId, Long postId, Boolean liked);

    void sendLikeCommentNotification(Long senderId, Long commentId, Boolean liked);

    void sendCommentPostNotification(Long senderId, Long postId, Long commentId);

    void sendCommentReplyNotification(Long senderId, Long postId, Long commentId, Long replyToCommentId);
}
