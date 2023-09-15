package usyd.elec5619.topicoservice.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface PostLikeMapper {
    @Select("SELECT liked FROM t_user_like_post WHERE user_id = #{userId} AND post_id = #{postId}")
    Boolean getPostLikedStatus(Long userId, Long postId);

    @Insert({"<script>",
            "insert into t_user_like_post (user_id, post_id, liked) values (#{userId}, #{postId}, 1);",
            "update t_post set likes = likes + 1 where id = #{postId};",
            "</script>"})
    void likePost(Long userId, Long postId);

    @Delete({
            "<script>",
            "delete from t_user_like_post where user_id = #{userId} and post_id = #{postId};",
            "update t_post set likes = likes - 1 where id = #{postId};",
            "</script>"
    })
    void unlikePost(Long userId, Long postId);

    @Insert({"<script>",
            "insert into t_user_like_post (user_id, post_id, liked) values (#{userId}, #{postId}, 0);",
            "update t_post set dislikes = dislikes + 1 where id = #{postId};",
            "</script>"})
    void dislikePost(Long userId, Long postId);

    @Delete({
            "<script>",
            "delete from t_user_like_post where user_id = #{userId} and post_id = #{postId};",
            "update t_post set dislikes = dislikes - 1 where id = #{postId};",
            "</script>"
    })
    void unDislikePost(Long userId, Long postId);

    @Select("SELECT likes from t_post where id = #{postId}")
    Integer getPostLikes(Long postId);

    @Select("SELECT dislikes from t_post where id = #{postId}")
    Integer getPostDislikes(Long postId);
}
