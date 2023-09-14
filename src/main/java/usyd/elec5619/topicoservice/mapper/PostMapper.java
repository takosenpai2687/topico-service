package usyd.elec5619.topicoservice.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import usyd.elec5619.topicoservice.model.Community;
import usyd.elec5619.topicoservice.model.Image;
import usyd.elec5619.topicoservice.model.Post;
import usyd.elec5619.topicoservice.vo.PostVO;

import java.util.List;

@Mapper
public interface PostMapper {
    @Select("SELECT id, community_id, author_id, title, SUBSTR(content, 0, 144) as `content`, spoiler, ctime, utime FROM t_post WHERE author_id = #{userId} ORDER BY ctime DESC LIMIT #{offset}, #{size}")
    List<Post> getPostsByUserId(Long userId, Integer offset, Integer size);


    @Select("SELECT COUNT(id) FROM t_post WHERE author_id = #{userId}")
    Integer countPostsByUserId(Long userId);

    @Select("SELECT image_uuid FROM t_post_image WHERE post_id = #{id}")
    List<String> getImagesByPostId(Long id);

    @Select("SELECT COUNT(id) FROM t_user_like_post WHERE post_id = #{id} AND `like` = TRUE")
    Integer countLikesByPostId(Long id);

    @Select("SELECT COUNT(id) FROM t_user_like_post WHERE post_id = #{id} AND `like` = FALSE")
    Integer countDislikesByPostId(Long id);


    @Select("SELECT COUNT(id) FROM t_comment WHERE post_id = #{id}")
    Integer countCommentsByPostId(Long id);

    @Select("SELECT * FROM t_post WHERE community_id = #{communityId} ORDER BY ctime DESC LIMIT #{offset}, #{size}")
    List<Post> getNewPostsByCommunityId(Long communityId, Integer offset, Integer size);

    @Select("SELECT * FROM t_post LEFT JOIN (SELECT post_id, COUNT(id) as count FROM t_user_like_post WHERE post_id = #{id} AND `like` = TRUE) AS `likes` ON " +
            "t_post.id = `likes`.post_id WHERE t_post.community_id = ${communityId} ORDER BY `likes`.count DESC LIMIT #{offset}, #{size}")
    List<Post> getHotPostsByCommunityId(Long communityId, Integer offset, Integer size);

    @Select("SELECT COUNT(id) FROM t_post WHERE community_id = #{communityId}")
    Integer countPostsByCommunityId(Long communityId);
}