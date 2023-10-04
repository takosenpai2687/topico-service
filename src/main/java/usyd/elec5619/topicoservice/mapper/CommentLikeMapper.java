package usyd.elec5619.topicoservice.mapper;

import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CommentLikeMapper {
    @Select("SELECT liked FROM t_user_like_comment WHERE user_id = #{userId} AND comment_id = #{commentId}")
    Boolean getCommentLikedStatus(Long userId, Long commentId);


    @Insert("insert into t_user_like_comment (user_id, comment_id, liked) values (#{userId}, #{commentId}, 1)")
    void insertLikeComment(Long userId, Long commentId);
    @Update("update t_comment set likes = likes + 1 where id = #{commentId}")
    void incrementCommentLikes(Long commentId);



    @Delete("delete from t_user_like_comment where user_id = #{userId} and comment_id = #{commentId}")
    void deleteLikeComment(Long userId, Long commentId);
    @Update("update t_comment set likes = likes - 1 where id = #{commentId}")
    void decrementCommentLikes(Long commentId);



    @Insert("insert into t_user_like_comment (user_id, comment_id, liked) values (#{userId}, #{commentId}, 0);")
    void insertDislikeComment(Long userId, Long commentId);
    @Update("update t_comment set dislikes = dislikes + 1 where id = #{commentId};")
    void incrementCommentDislike(Long commentId);



    @Delete("delete from t_user_like_comment where user_id = #{userId} and comment_id = #{commentId};")
    void deleteDislikeComment(Long userId, Long commentId);
    @Update("update t_comment set dislikes = dislikes - 1 where id = #{commentId};")
    void decrementCommentDislike(Long commentId);



    @Select("SELECT likes from t_comment where id = #{commentId}")
    Integer getCommentLikes(Long commentId);
    @Select("SELECT dislikes from t_comment where id = #{commentId}")
    Integer getCommentDislikes(Long commentId);



    @Delete("DELETE FROM t_user_like_comment WHERE comment_id = #{commentId}")
    void deleteCommentLikesOrDislikes(Long commentId);


}
