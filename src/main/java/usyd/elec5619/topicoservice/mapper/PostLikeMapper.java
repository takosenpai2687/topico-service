package usyd.elec5619.topicoservice.mapper;

import org.apache.ibatis.annotations.*;

@Mapper
public interface PostLikeMapper {
    @Select("SELECT liked FROM t_user_like_post WHERE user_id = #{userId} AND post_id = #{postId}")
    Boolean getPostLikedStatus(Long userId, Long postId);

    // For likePost
    @Insert("INSERT INTO t_user_like_post (user_id, post_id, liked) VALUES (#{userId}, #{postId}, 1)")
    void insertUserLikePost(Long userId, Long postId);
    @Update("UPDATE t_post SET likes = likes + 1 WHERE id = #{postId}")
    void updatePostLikes(Long postId);


    // For unlikePost
    @Delete("DELETE FROM t_user_like_post WHERE user_id = #{userId} AND post_id = #{postId}")
    void deleteUserLikePost(Long userId, Long postId);
    @Update("UPDATE t_post SET likes = likes - 1 WHERE id = #{postId}")
    void decrementPostLikes(Long postId);


    // For dislikePost
    @Insert("INSERT INTO t_user_like_post (user_id, post_id, liked) VALUES (#{userId}, #{postId}, 0)")
    void insertUserDislikePost(Long userId, Long postId);
    @Update("UPDATE t_post SET dislikes = dislikes + 1 WHERE id = #{postId}")
    void incrementPostDislikes(Long postId);


    // For unDislikePost
    @Delete("DELETE FROM t_user_like_post WHERE user_id = #{userId} AND post_id = #{postId}")
    void deleteUserDislikePost(Long userId, Long postId);
    @Update("UPDATE t_post SET dislikes = dislikes - 1 WHERE id = #{postId}")
    void decrementPostDislikes(Long postId);


    @Select("SELECT likes from t_post where id = #{postId}")
    Integer getPostLikes(Long postId);

    @Select("SELECT dislikes from t_post where id = #{postId}")
    Integer getPostDislikes(Long postId);
}
