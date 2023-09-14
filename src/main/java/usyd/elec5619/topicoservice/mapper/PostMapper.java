package usyd.elec5619.topicoservice.mapper;

import org.apache.ibatis.annotations.*;
import usyd.elec5619.topicoservice.model.*;
import usyd.elec5619.topicoservice.vo.PostVO;

import java.util.List;

@Mapper
public interface PostMapper {
    @Select("SELECT " +
            "id,title," +
            "SUBSTR(content, 0, 144) shortContent," +
            "spoiler," +
            "ctime,utime," +
            "community_id," +
            "author_id," +
            "likes," +
            "dislikes," +
            "replies" +
            "FROM t_post " +
            "WHERE author_id = #{userId} ORDER BY ctime DESC LIMIT #{offset}, #{size}")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "title", property = "title"),
            @Result(column = "shortContent", property = "content"),
            @Result(column = "spoiler", property = "spoiler"),
            @Result(column = "likes", property = "likes"),
            @Result(column = "dislikes", property = "dislikes"),
            @Result(column = "replies", property = "replies"),
            @Result(column = "ctime", property = "ctime"),
            @Result(column = "utime", property = "utime"),
            @Result(column = "community_id", property = "community", javaType = Community.class, one = @One(select = "CommunityMapper.getCommunityById")),
            @Result(column = "author_id", property = "author", javaType = User.class, one = @One(select = "UserMapper.getUserById")),
            @Result(column = "id", property = "tags", javaType = List.class, many = @Many(select = "TagMapper.getTagsByPostId")),
            @Result(column = "id", property = "images", javaType = List.class, many = @Many(select = "getImageUuidsByPostId"))
    })
    List<PostVO> getPostsByUserId(Long userId, Integer offset, Integer size);

    @Select("SELECT COUNT(id) FROM t_post WHERE author_id = #{userId}")
    Integer countPostsByUserId(Long userId);

    @Select("SELECT image_uuid FROM t_post_image WHERE post_id = #{id}")
    List<String> getImageUuidsByPostId(Long id);


    @Select("SELECT " +
            "id,title," +
            "SUBSTR(content, 0, 144) shortContent," +
            "spoiler," +
            "ctime,utime," +
            "community_id," +
            "author_id," +
            "likes," +
            "dislikes " +
            "FROM t_post " +
            "WHERE community_id = #{communityId} ORDER BY ctime DESC LIMIT #{offset}, #{size}")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "title", property = "title"),
            @Result(column = "shortContent", property = "content"),
            @Result(column = "spoiler", property = "spoiler"),
            @Result(column = "likes", property = "likes"),
            @Result(column = "dislikes", property = "dislikes"),
            @Result(column = "ctime", property = "ctime"),
            @Result(column = "utime", property = "utime"),
            @Result(column = "community_id", property = "community", javaType = Community.class, one = @One(select = "CommunityMapper.getCommunityById")),
            @Result(column = "author_id", property = "author", javaType = User.class, one = @One(select = "UserMapper.getUserById")),
            @Result(column = "id", property = "tags", javaType = List.class, many = @Many(select = "TagMapper.getTagsByPostId")),
            @Result(column = "id", property = "commentsCount", javaType = Integer.class, one = @One(select = "CommentMapper.countCommentsByPostId")),
            @Result(column = "id", property = "images", javaType = List.class, many = @Many(select = "getImageUuidsByPostId"))
    })
    List<PostVO> getNewPostsByCommunityId(Long communityId, Integer offset, Integer size);

    @Select("SELECT " +
            "id,title," +
            "SUBSTR(content, 0, 144) shortContent," +
            "spoiler," +
            "ctime,utime," +
            "community_id," +
            "author_id," +
            "likes," +
            "dislikes " +
            "FROM t_post " +
            "WHERE community_id = #{communityId} ORDER BY likes DESC LIMIT #{offset}, #{size}")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "title", property = "title"),
            @Result(column = "shortContent", property = "content"),
            @Result(column = "spoiler", property = "spoiler"),
            @Result(column = "likes", property = "likes"),
            @Result(column = "dislikes", property = "dislikes"),
            @Result(column = "ctime", property = "ctime"),
            @Result(column = "utime", property = "utime"),
            @Result(column = "community_id", property = "community", javaType = Community.class, one = @One(select = "CommunityMapper.getCommunityById")),
            @Result(column = "author_id", property = "author", javaType = User.class, one = @One(select = "UserMapper.getUserById")),
            @Result(column = "id", property = "tags", javaType = List.class, many = @Many(select = "TagMapper.getTagsByPostId")),
            @Result(column = "id", property = "commentsCount", javaType = Integer.class, one = @One(select = "CommentMapper.countCommentsByPostId")),
            @Result(column = "id", property = "images", javaType = List.class, many = @Many(select = "getImageUuidsByPostId"))
    })
    List<PostVO> getHotPostsByCommunityId(Long communityId, Integer offset, Integer size);

    @Select("SELECT COUNT(id) FROM t_post WHERE community_id = #{communityId}")
    Integer countPostsByCommunityId(Long communityId);

    @Select("SELECT title from t_post WHERE id = #{id} LIMIT 1")
    String getPostTitleById(Long id);
}