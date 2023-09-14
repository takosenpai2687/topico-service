package usyd.elec5619.topicoservice.mapper;

import org.apache.ibatis.annotations.*;
import org.springframework.data.annotation.QueryAnnotation;
import org.springframework.transaction.annotation.Transactional;

@Mapper
public interface LikeMapper {

    @Select("SELECT EXISTS(SELECT 1 FROM t_user_like_post WHERE user_id = #{userId} AND post_id = #{postId})")
    Boolean getPostLikedStatus(Long userId, Long postId);

    @Select("SELECT EXISTS(SELECT 1 FROM t_user_like_comment WHERE user_id = #{userId} AND comment_id = #{commentId})")
    Boolean getCommentLikedStatus(Long userId, Long commentId);


    @Insert({
            "<script>",
            "insert into t_user_like_post (user_id, post_id) values (#{userId}, #{postId});",
            "update t_post set likes = likes + 1 where id = #{postId};",
            "</script>"
    })
    void likePost(Long userId, Long postId);

    @Insert({
            "<script>",
            "insert into t_user_like_post (user_id, post_id) values (#{userId}, #{postId});",
            "update t_comment set likes = likes + 1 where id = #{commentId};",
            "</script>"
    })
    void likeComment(Long userId, Long commentId);

    @Update({
            "<script>",
            "delete from t_user_like_post where user_id = #{userId} and post_id = #{postId};",
            "update t_post set likes = likes - 1 where id = #{postId};",
            "</script>"
    })
    void unlikePost(Long userId, Long postId);

    @Update({"<script>",
            "delete from t_user_like_comment where user_id = #{userId} and comment_id = #{commentId};",
            "update t_comment set likes = likes - 1 where id = #{commentId}",
            "</script>"
    })
    void unlikeComment(Long userId, Long commentId);


}
