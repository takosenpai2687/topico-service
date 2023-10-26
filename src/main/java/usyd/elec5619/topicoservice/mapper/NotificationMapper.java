package usyd.elec5619.topicoservice.mapper;

import org.apache.ibatis.annotations.*;
import usyd.elec5619.topicoservice.model.Notification;
import usyd.elec5619.topicoservice.model.User;
import usyd.elec5619.topicoservice.type.NotificationType;
import usyd.elec5619.topicoservice.vo.NotificationVO;

import java.util.List;
import java.util.Optional;

@Mapper
public interface NotificationMapper {

    @Select("SELECT * FROM t_notification WHERE receiver_id = #{receiverId} ORDER BY ctime DESC")
    List<Notification> getAllNotifications(Long receiverId);

    @Select("SELECT * FROM t_notification WHERE id = #{notificationId}")
    Optional<Notification> getNotificationById(Long notificationId);

    @Update("UPDATE t_notification SET unread = #{unread} WHERE id = #{id}")
    void updateNotification(Notification notification);


    @Delete("DELETE FROM t_notification WHERE type = 'LIKE_POST' " +
            "AND sender_id = #{senderId} " +
            "AND receiver_id = #{receiverId} AND post_id = #{postId}")
    void deleteLikePost(Long senderId, Long receiverId, Long postId);

    @Insert("INSERT INTO t_notification(type, sender_id, receiver_id, post_id, comment_id, reply_to_comment_id, content, unread) " +
            "VALUES(#{type}, #{senderId}, #{receiverId}, #{postId}, #{commentId}, #{replyToCommentId}, #{content}, #{unread})")
    void insertOne(Notification notification);

    @Delete("DELETE FROM t_notification WHERE type = 'LIKE_COMMENT' " +
            "AND sender_id = #{senderId} " +
            "AND receiver_id = #{receiverId} " +
            "AND comment_id = #{commentId}")
    void deleteLikeComment(Long senderId, Long receiverId, Long commentId);

    @Select("SELECT author_id FROM t_post WHERE id = #{postid}")
    Long getPostAuthorId(Long postId);

    @Select("SELECT author_id FROM t_comment WHERE id = #{commentId}")
    Long getCommentAuthorId(Long commentId);

    @Delete("DELETE FROM t_notification WHERE post_id = #{id}")
    void deleteAllNotificationsByPostId(Long id);

    @Delete("DELETE FROM t_notification WHERE comment_id = #{id}")
    void deleteAllNotificationsByCommentId(Long id);
}