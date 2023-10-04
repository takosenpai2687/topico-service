package usyd.elec5619.topicoservice.mapper;

import org.apache.ibatis.annotations.*;
import usyd.elec5619.topicoservice.model.Comment;
import usyd.elec5619.topicoservice.vo.CommentVO;

import java.util.List;
import java.util.Optional;

@Mapper
public interface CommentMapper {
    @Select("SELECT COUNT(id) FROM t_comment WHERE author_id = #{userId} AND parent_id IS NULL")
    Integer countCommentsByUserId(Long userId);

    @Select("SELECT COUNT(id) FROM t_comment WHERE post_id = #{postId} AND parent_id IS NULL")
    Integer countCommentsByPostId(Long postId);

    @Select("SELECT COUNT(id) FROM t_comment WHERE parent_id = #{commentId}")
    Integer countRepliesByCommentId(Long commentId);

    @Select("SELECT * FROM t_comment " +
            "WHERE author_id = #{userId} AND parent_id IS NULL " +
            "ORDER BY ctime DESC " +
            "LIMIT #{offset}, #{size}")
    List<Comment> getCommentsByUserId(Long userId, Integer offset, Integer size);

    @Select("SELECT * FROM t_comment " +
            "WHERE post_id = #{postId} AND parent_id IS NULL " +
            "ORDER BY likes DESC, ctime DESC " +
            "LIMIT #{offset}, #{size}")
    List<Comment> getHotAndNewCommentsByPostId(Long postId, Integer offset, Integer size);

    //
    @Select("SELECT * FROM t_comment WHERE parent_id = #{commentId} ORDER BY  likes DESC, ctime DESC  LIMIT #{offset}, #{size}")
    List<Comment> getRepliesByCommentId(Long commentId, Integer offset, Integer size);

    @Select("SELECT id FROM  t_comment WHERE parent_id = #{commentId}")
    List<Long> getRepliesIdByCommentId(Long commentId);

    @Insert("INSERT INTO t_comment " +
            "(post_id, author_id, parent_id, reply_to_user_id, content) " +
            "VALUES " +
            "(#{postId}, #{authorId}, #{parentId}, #{replyToUserId}, #{content})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    void insertOne(Comment comment);

    @Select("SELECT * FROM t_comment WHERE id = #{id} LIMIT 1")
    Optional<Comment> getCommentById(Long id);

    @Delete("DELETE FROM t_comment WHERE id = #{commentId}")
    void deleteOne(Long commentId);

    @Select("SELECT * FROM t_comment WHERE post_id = #{id}")
    List<Comment> getCommentsByPostId(Long id);

    @Select("SELECT id FROM t_comment WHERE post_id = #{postId}")
    List<Long> getCommentIdsByPostId(Long postId);

    @Update("UPDATE t_comment SET replies = replies + 1 WHERE id = #{postId}")
    void addReplyToComment(Long commentId);
    @Update("UPDATE t_comment SET replies = CASE WHEN replies > 0 THEN replies - 1 ELSE 0 END WHERE id = #{postId}")
    void decrementReplyToComment(Long commentId);
}