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

    @Select("SELECT id," +
            "type," +
            "sender_id AS senderId," +
            "receiver_id AS receiverId," +
            "post_id  AS postId," +
            "comment_id AS commentId," +
            "reply_to_comment_id AS replyToCommentId," +
            "content," +
            "unread," +
            "ctime," +
            "utime " +
            " FROM t_notification WHERE receiver_id = #{receiverId} " +
            "AND type = 'LIKE_POST' ORDER BY ctime")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "type", property = "type", javaType = NotificationType.class),
            @Result(column = "senderId", property = "sender", javaType = usyd.elec5619.topicoservice.model.User.class, one = @One(select = "usyd.elec5619.topicoservice.mapper.UserMapper.getUserById")),
            @Result(column = "receiverId", property = "receiver", javaType = usyd.elec5619.topicoservice.model.User.class, one = @One(select = "usyd.elec5619.topicoservice.mapper.UserMapper.getUserById")),
            @Result(column = "postId", property = "post", javaType = usyd.elec5619.topicoservice.vo.PostVO.class, one = @One(select = "usyd.elec5619.topicoservice.mapper.PostMapper.getPostById")),
            @Result(column = "commentId", property = "comment", javaType = usyd.elec5619.topicoservice.vo.CommentVO.class, one = @One(select = "usyd.elec5619.topicoservice.mapper.CommentMapper.getCommentById")),
            @Result(column = "replyToCommentId", property = "replyToComment", javaType = usyd.elec5619.topicoservice.vo.CommentVO.class, one = @One(select = "usyd.elec5619.topicoservice.mapper.CommentMapper.getCommentById")),
            @Result(column = "postId", property = "total", javaType = Integer.class, one = @One(select = "usyd.elec5619.topicoservice.mapper.PostLikeMapper.getPostLikes")),
            @Result(column = "content", property = "content"),
            @Result(column = "unread", property = "unread", javaType = Boolean.class),
            @Result(column = "ctime", property = "ctime"),
            @Result(column = "utime", property = "utime")
    })
    List<NotificationVO> getLikePostNotifications(Long receiverId);

    @Select("SELECT id," +
            "type," +
            "sender_id AS senderId," +
            "receiver_id AS receiverId," +
            "post_id AS postId," +
            "comment_id AS commentId," +
            "reply_to_comment_id AS replyToCommentId," +
            "content," +
            "unread," +
            "ctime," +
            "utime " +
            " FROM t_notification WHERE receiver_id = #{receiverId} " +
            "AND type = 'LIKE_COMMENT' ORDER BY ctime DESC")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "type", property = "type", javaType = NotificationType.class),
            @Result(column = "senderId", property = "sender", javaType = usyd.elec5619.topicoservice.model.User.class, one = @One(select = "usyd.elec5619.topicoservice.mapper.UserMapper.getUserById")),
            @Result(column = "receiverId", property = "receiver", javaType = usyd.elec5619.topicoservice.model.User.class, one = @One(select = "usyd.elec5619.topicoservice.mapper.UserMapper.getUserById")),
            @Result(column = "postId", property = "post", javaType = usyd.elec5619.topicoservice.vo.PostVO.class, one = @One(select = "usyd.elec5619.topicoservice.mapper.PostMapper.getPostById")),
            @Result(column = "commentId", property = "comment", javaType = usyd.elec5619.topicoservice.vo.CommentVO.class, one = @One(select = "usyd.elec5619.topicoservice.mapper.CommentMapper.getCommentById")),
            @Result(column = "replyToCommentId", property = "replyToComment", javaType = usyd.elec5619.topicoservice.vo.CommentVO.class, one = @One(select = "usyd.elec5619.topicoservice.mapper.CommentMapper.getCommentById")),
            @Result(column = "commentId", property = "total", javaType = Integer.class, one = @One(select = "usyd.elec5619.topicoservice.mapper.CommentLikeMapper.getCommentLikes")),
            @Result(column = "content", property = "content"),
            @Result(column = "unread", property = "unread", javaType = Boolean.class),
            @Result(column = "ctime", property = "ctime"),
            @Result(column = "utime", property = "utime")
    })
    List<NotificationVO> getLikeCommentNotifications(Long receiverId);

    @Select("SELECT id," +
            "type," +
            "sender_id AS senderId," +
            "receiver_id AS receiverId," +
            "post_id AS postId," +
            "comment_id AS commentId," +
            "reply_to_comment_id AS replyToCommentId," +
            "content," +
            "unread," +
            "ctime," +
            "utime " +
            " FROM t_notification WHERE receiver_id = #{receiverId} " +
            "AND type = 'COMMENT_POST' ORDER BY ctime DESC")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "type", property = "type", javaType = NotificationType.class),
            @Result(column = "senderId", property = "sender", javaType = usyd.elec5619.topicoservice.model.User.class, one = @One(select = "usyd.elec5619.topicoservice.mapper.UserMapper.getUserById")),
            @Result(column = "receiverId", property = "receiver", javaType = usyd.elec5619.topicoservice.model.User.class, one = @One(select = "usyd.elec5619.topicoservice.mapper.UserMapper.getUserById")),
            @Result(column = "postId", property = "post", javaType = usyd.elec5619.topicoservice.vo.PostVO.class, one = @One(select = "usyd.elec5619.topicoservice.mapper.PostMapper.getPostById")),
            @Result(column = "commentId", property = "comment", javaType = usyd.elec5619.topicoservice.vo.CommentVO.class, one = @One(select = "usyd.elec5619.topicoservice.mapper.CommentMapper.getCommentById")),
            @Result(column = "replyToCommentId", property = "replyToComment", javaType = usyd.elec5619.topicoservice.vo.CommentVO.class, one = @One(select = "usyd.elec5619.topicoservice.mapper.CommentMapper.getCommentById")),
            @Result(column = "postId", property = "total", javaType = Integer.class, one = @One(select = "usyd.elec5619.topicoservice.mapper.CommentMapper.countCommentsByPostId")),
            @Result(column = "content", property = "content"),
            @Result(column = "unread", property = "unread", javaType = Boolean.class),
            @Result(column = "ctime", property = "ctime"),
            @Result(column = "utime", property = "utime")
    })
    List<NotificationVO> getCommentPostNotifications(Long receiverId);

    @Select("SELECT id," +
            "type," +
            "sender_id AS senderId," +
            "receiver_id AS receiverId," +
            "post_id AS postId," +
            "comment_id AS commentId," +
            "reply_to_comment_id AS replyToCommentId," +
            "content," +
            "unread," +
            "ctime," +
            "utime " +
            " FROM t_notification WHERE receiver_id = #{receiverId} " +
            "AND type = 'COMMENT_COMMENT' ORDER BY ctime DESC")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "type", property = "type", javaType = NotificationType.class),
            @Result(column = "senderId", property = "sender", javaType = usyd.elec5619.topicoservice.model.User.class, one = @One(select = "usyd.elec5619.topicoservice.mapper.UserMapper.getUserById")),
            @Result(column = "receiverId", property = "receiver", javaType = usyd.elec5619.topicoservice.model.User.class, one = @One(select = "usyd.elec5619.topicoservice.mapper.UserMapper.getUserById")),
            @Result(column = "postId", property = "post", javaType = usyd.elec5619.topicoservice.vo.PostVO.class, one = @One(select = "usyd.elec5619.topicoservice.mapper.PostMapper.getPostById")),
            @Result(column = "commentId", property = "comment", javaType = usyd.elec5619.topicoservice.vo.CommentVO.class, one = @One(select = "usyd.elec5619.topicoservice.mapper.CommentMapper.getCommentById")),
            @Result(column = "replyToCommentId", property = "replyToComment", javaType = usyd.elec5619.topicoservice.vo.CommentVO.class, one = @One(select = "usyd.elec5619.topicoservice.mapper.CommentMapper.getCommentById")),
            @Result(column = "commentId", property = "total", javaType = Integer.class, one = @One(select = "usyd.elec5619.topicoservice.mapper.CommentMapper.countRepliesByCommentId")),
            @Result(column = "content", property = "content"),
            @Result(column = "unread", property = "unread", javaType = Boolean.class),
            @Result(column = "ctime", property = "ctime"),
            @Result(column = "utime", property = "utime")
    })
    List<NotificationVO> getCommentReplyNotifications(Long receiverId);
}