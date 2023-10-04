package usyd.elec5619.topicoservice.mapper;

import org.apache.ibatis.annotations.*;
import usyd.elec5619.topicoservice.model.Community;
import usyd.elec5619.topicoservice.model.Post;
import usyd.elec5619.topicoservice.model.User;
import usyd.elec5619.topicoservice.vo.PostVO;

import java.util.List;
import java.util.Optional;

@Mapper
public interface PostMapper {

    @Select("SELECT * FROM t_post WHERE author_id = #{userId} ORDER BY ctime DESC LIMIT #{offset}, #{size}")
    List<Post> getPostsByUserId(Long userId, Integer offset, Integer size);

    @Select("SELECT COUNT(id) FROM t_post WHERE author_id = #{userId}")
    Integer countPostsByUserId(Long userId);

    @Select("SELECT id FROM t_post_image WHERE post_id = #{id}")
    List<String> getImagesByPostId(Long id);


    @Select("SELECT * FROM t_post WHERE community_id = #{communityId} ORDER BY likes DESC, ctime DESC LIMIT #{offset}, #{size}")
    List<Post> getOrderedPostsByCommunityId(Long communityId, Integer offset, Integer size);


    @Select("SELECT COUNT(id) FROM t_post WHERE community_id = #{communityId}")
    Integer countPostsByCommunityId(Long communityId);

    @Select("SELECT title from t_post WHERE id = #{id} LIMIT 1")
    String getPostTitleById(Long id);

    @Select("SELECT COUNT(id) FROM t_post WHERE title LIKE CONCAT('%', #{keyword}, '%')")
    Integer countSearchByTitle(String keyword);

    @Select("SELECT * FROM t_post WHERE title LIKE CONCAT('%', #{keyword}, '%') OR tags LIKE CONCAT('%', #{keyword}, '%') ORDER BY likes DESC, ctime DESC LIMIT #{offset}, #{size}")
    List<Post> searchPostsByKeyword(String keyword, int offset, Integer size);

    @Insert("INSERT INTO t_post (community_id, author_id, title, content, spoiler, tags) VALUES (#{communityId}, #{authorId}, #{title}, #{content}, #{spoiler}, #{tags})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    void insertOne(Post post);

    @Select("SELECT * FROM t_post WHERE id = #{postId} LIMIT 1")
    Optional<Post> getPostById(Long postId);

    @Delete("DELETE FROM t_post WHERE id = #{postId}")
    void deleteOne(Long postId);

    @Select("SELECT * FROM t_post WHERE community_id = #{communityId}")
    List<Post> getPostsByCommunityId(Long communityId);

    @Delete("DELETE FROM t_user_like_post WHERE post_id = #{id}")
    void deleteAllLikesByPostId(Long id);

    @Select("SELECT author_id FROM t_post WHERE id = #{postId} LIMIT 1")
    Long getAuthorIdByPostId(Long postId);

    @Update("UPDATE t_post SET replies = replies + 1 WHERE id = #{postId}")
    void addReplyToPost(Long postId);

    @Update("UPDATE t_post SET replies = CASE WHEN replies > 0 THEN replies - 1 ELSE 0 END WHERE id = #{postId}")
    void decrementReplyToPost(Long postId);
}