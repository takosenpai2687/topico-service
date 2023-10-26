package usyd.elec5619.topicoservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import usyd.elec5619.topicoservice.exception.http.BadRequestException;
import usyd.elec5619.topicoservice.mapper.CommentMapper;
import usyd.elec5619.topicoservice.mapper.NotificationMapper;
import usyd.elec5619.topicoservice.mapper.PostMapper;
import usyd.elec5619.topicoservice.mapper.UserMapper;
import usyd.elec5619.topicoservice.model.Notification;
import usyd.elec5619.topicoservice.model.Post;
import usyd.elec5619.topicoservice.model.User;
import usyd.elec5619.topicoservice.service.NotificationService;
import usyd.elec5619.topicoservice.service.PostService;
import usyd.elec5619.topicoservice.type.NotificationType;
import usyd.elec5619.topicoservice.vo.NotificationVO;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final NotificationMapper notificationMapper;
    private final UserMapper userMapper;
    private final PostMapper postMapper;
    private final CommentMapper commentMapper;

    @Override
    public List<NotificationVO> getAllNotifications(Long receiverId) {
        List<Notification> notifications = notificationMapper.getAllNotifications(receiverId);
        User receiver = userMapper.getUserById(receiverId).orElseThrow(() -> new BadRequestException("Receiver not found"));
        return notifications.stream().map(notification -> NotificationVO.builder()
                                                                        .id(notification.getId())
                                                                        .type(notification.getType())
                                                                        .sender(userMapper.getUserById(notification.getSenderId()).orElse(null))
                                                                        .receiver(receiver).post(postMapper.getPostById(notification.getPostId()).orElse(null))
                                                                        .comment(commentMapper.getCommentById(notification.getCommentId()).orElse(null))
                                                                        .replyToComment(commentMapper.getCommentById(notification.getReplyToCommentId()).orElse(null))
                                                                        .content(notification.getContent())
                                                                        .unread(notification.getUnread())
                                                                        .ctime(notification.getCtime())
                                                                        .utime(notification.getUtime())
                                                                        .build()).toList();
    }

    @Override
    public void readNotification(Long receiverId, Long notificationId) {
        Notification notification = notificationMapper.getNotificationById(notificationId).orElseThrow(() -> new BadRequestException("Notification invalid"));
        if (!notification.getReceiverId().equals(receiverId)) {
            throw new BadRequestException("Notification invalid");
        }
        if (!notification.getUnread()) {
            throw new BadRequestException("Notification already read");
        }

        notification.setUnread(false);
        notificationMapper.updateNotification(notification);
    }

    @Override
    public void sendLikePostNotification(Long senderId, Long postId, Boolean liked) {
        Long receiverId = notificationMapper.getPostAuthorId(postId);
        // Don't send notification to self
        if (senderId.equals(receiverId)) {
            return;
        }
        if (!liked) {
            notificationMapper.deleteLikePost(senderId, receiverId, postId);
            return;
        }
        Notification notification = Notification.builder().type(NotificationType.LIKE_POST).senderId(senderId).receiverId(receiverId).postId(postId).commentId(null).unread(true).build();
        notificationMapper.insertOne(notification);
    }

    @Override
    public void sendLikeCommentNotification(Long senderId, Long commentId, Boolean liked) {
        Long receiverId = notificationMapper.getCommentAuthorId(commentId);
        // Don't send notification to self
        if (senderId.equals(receiverId)) {
            return;
        }
        if (!liked) {
            notificationMapper.deleteLikeComment(senderId, receiverId, commentId);
            return;
        }
        Notification notification = Notification.builder().type(NotificationType.LIKE_COMMENT).senderId(senderId).receiverId(receiverId).postId(null).commentId(commentId).unread(true).build();
        notificationMapper.insertOne(notification);
    }

    @Override
    public void sendCommentPostNotification(Long senderId, Long postId, Long commentId) {
        Long receiverId = notificationMapper.getPostAuthorId(postId);
        Notification notification = Notification.builder().type(NotificationType.COMMENT_POST).senderId(senderId).receiverId(receiverId).postId(postId).commentId(commentId).unread(true).build();
        notificationMapper.insertOne(notification);
    }

    @Override
    public void sendCommentReplyNotification(Long senderId, Long postId, Long commentId, Long replyToCommentId) {
        Long receiverId = notificationMapper.getCommentAuthorId(replyToCommentId);
        Notification notification = Notification.builder().type(NotificationType.COMMENT_REPLY).senderId(senderId).receiverId(receiverId).postId(postId).commentId(commentId).replyToCommentId(replyToCommentId).unread(true).build();
        notificationMapper.insertOne(notification);
    }

    @Override
    public void deleteAllNotificationsByPostId(Long id) {
        notificationMapper.deleteAllNotificationsByPostId(id);
    }

    @Override
    public void deleteAllNotificationsByCommentId(Long id) {
        notificationMapper.deleteAllNotificationsByCommentId(id);
    }
}
