package usyd.elec5619.topicoservice.mapper;

import org.apache.ibatis.annotations.*;

@Mapper
public interface CommentLikeMapper {
    @Select("SELECT liked FROM t_user_like_comment WHERE user_id = #{userId} AND comment_id = #{commentId}")
    Boolean getCommentLikedStatus(Long userId, Long commentId);

//    @Insert({"<script>",
//            "insert into t_user_like_post (user_id, post_id, liked) values (#{userId}, #{postId}, 1);",
//            "update t_comment set likes = likes + 1 where id = #{commentId};",
//            "</script>"})
//    void likeComment(Long userId, Long commentId);
    @Insert("insert into t_user_like_comment (user_id, comment_id, liked) values (#{userId}, #{commentId}, 1)")
    void insertUserLikeComment(Long userId, Long commentId);

    @Update("update t_comment set likes = likes + 1 where id = #{commentId}")
    void incrementCommentLikes(Long commentId);



//    @Delete({
//            "<script>",
//            "delete from t_user_like_comment where user_id = #{userId} and comment_id = #{commentId};",
//            "update t_comment set likes = likes - 1 where id = #{commentId};",
//            "</script>"
//    })
//    void unlikeComment(Long userId, Long commentId);
    @Delete("delete from t_user_like_comment where user_id = #{userId} and comment_id = #{commentId}")
    void deleteUserLikePost(Long userId, Long commentId);
    @Update("update t_comment set likes = likes - 1 where id = #{commentId}")
    void decrementCommentLikes(Long commentId);



//    @Insert({"<script>",
//            "insert into t_user_like_comment (user_id, comment_id, liked) values (#{userId}, #{commentId}, 0);",
//            "update t_comment set dislikes = dislikes + 1 where id = #{commentId};",
//            "</script>"})
//    void dislikeComment(Long userId, Long commentId);
    @Insert("insert into t_user_like_comment (user_id, comment_id, liked) values (#{userId}, #{commentId}, 0);")
    void insertDislikeForComment(Long userId, Long commentId);

    @Update("update t_comment set dislikes = dislikes + 1 where id = #{commentId};")
    void incrementDislikeForComment(Long commentId);


//
//    @Delete({
//            "<script>",
//            "delete from t_user_like_comment where user_id = #{userId} and comment_id = #{commentId};",
//            "update t_comment set dislikes = dislikes - 1 where id = #{commentId};",
//            "</script>"
//    })
//    void unDislikeComment(Long userId, Long commentId);
    @Delete("delete from t_user_like_comment where user_id = #{userId} and comment_id = #{commentId};")
    void deleteDislikeForComment(Long userId, Long commentId);

    @Update("update t_comment set dislikes = dislikes - 1 where id = #{commentId};")
    void decrementDislikeForComment(Long commentId);



    @Select("SELECT likes from t_comment where id = #{commentId}")
    Integer getCommentLikes(Long commentId);

    @Select("SELECT dislikes from t_comment where id = #{commentId}")
    Integer getCommentDislikes(Long commentId);
}
