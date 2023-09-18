package usyd.elec5619.topicoservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import usyd.elec5619.topicoservice.exception.http.BadRequestException;
import usyd.elec5619.topicoservice.mapper.NotificationMapper;
import usyd.elec5619.topicoservice.model.Notification;
import usyd.elec5619.topicoservice.service.NotificationService;
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

    @Override
    public List<NotificationVO> getAllNotifications(Long receiverId) {
        // Get all notifications
        List<NotificationVO> likePostNotifications = notificationMapper.getLikePostNotifications(receiverId);
        List<NotificationVO> likeCommentNotifications = notificationMapper.getLikeCommentNotifications(receiverId);
        List<NotificationVO> commentPostNotifications = notificationMapper.getCommentPostNotifications(receiverId);
        List<NotificationVO> commentReplyNotifications = notificationMapper.getCommentReplyNotifications(receiverId);
        // Deduplicate
        Set<NotificationVO> likePostSet = new TreeSet<>(Comparator.comparing(o -> o.getPost().getId()));
        Set<NotificationVO> likeCommentSet = new TreeSet<>(Comparator.comparing(o -> o.getComment().getId()));
        Set<NotificationVO> commentPostSet = new TreeSet<>(Comparator.comparing(o -> o.getPost().getId()));
        Set<NotificationVO> commentReplySet = new TreeSet<>(Comparator.comparing(o -> o.getComment().getId()));
        likePostSet.addAll(likePostNotifications);
        likeCommentSet.addAll(likeCommentNotifications);
        commentPostSet.addAll(commentPostNotifications);
        commentReplySet.addAll(commentReplyNotifications);

        return Stream.of(likePostSet, likeCommentSet, commentPostSet, commentReplySet).toList().stream()
                     .flatMap(Set::stream)
                     .sorted(Comparator.comparing(NotificationVO::getCtime).reversed())
                     .collect(Collectors.toList());
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
        if (!liked) {
            notificationMapper.deleteLikePost(senderId, receiverId, postId);
            return;
        }
        Notification notification = Notification.builder()
                                                .type(NotificationType.LIKE_POST)
                                                .senderId(senderId)
                                                .receiverId(receiverId)
                                                .postId(postId)
                                                .commentId(null)
                                                .unread(true)
                                                .build();
        notificationMapper.insertOne(notification);
    }

    @Override
    public void sendLikeCommentNotification(Long senderId, Long commentId, Boolean liked) {
        Long receiverId = notificationMapper.getCommentAuthorId(commentId);
        if (!liked) {
            notificationMapper.deleteLikeComment(senderId, receiverId, commentId);
            return;
        }
        Notification notification = Notification.builder()
                                                .type(NotificationType.LIKE_COMMENT)
                                                .senderId(senderId)
                                                .receiverId(receiverId)
                                                .postId(null)
                                                .commentId(commentId)
                                                .unread(true)
                                                .build();
        notificationMapper.insertOne(notification);
    }

    @Override
    public void sendCommentPostNotification(Long senderId, Long postId, Long commentId) {
        Long receiverId = notificationMapper.getPostAuthorId(postId);
        Notification notification = Notification.builder()
                                                .type(NotificationType.COMMENT_POST)
                                                .senderId(senderId)
                                                .receiverId(receiverId)
                                                .postId(postId)
                                                .commentId(commentId)
                                                .unread(true)
                                                .build();
        notificationMapper.insertOne(notification);
    }

    @Override
    public void sendCommentReplyNotification(Long senderId, Long postId, Long commentId, Long replyToCommentId) {
        Long receiverId = notificationMapper.getCommentAuthorId(replyToCommentId);
        Notification notification = Notification.builder()
                                                .type(NotificationType.COMMENT_REPLY)
                                                .senderId(senderId)
                                                .receiverId(receiverId)
                                                .postId(postId)
                                                .commentId(commentId)
                                                .replyToCommentId(replyToCommentId)
                                                .unread(true)
                                                .build();
        notificationMapper.insertOne(notification);
    }
}
