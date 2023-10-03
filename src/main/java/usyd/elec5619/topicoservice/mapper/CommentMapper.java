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

    @Select("SELECT " +
            "id," +
            "post_id," +
            "author_id," +
            "content," +
            "likes," +
            "dislikes," +
            "replies," +
            "ctime," +
            "utime " +
            "FROM t_comment " +
            "WHERE author_id = #{userId} AND parent_id IS NULL " +
            "ORDER BY ctime DESC " +
            "LIMIT #{offset}, #{size}")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "post_id", property = "postId"),
            @Result(column = "post_id", property = "postTitle", javaType = String.class, one = @One(select = "usyd.elec5619.topicoservice.mapper.PostMapper.getPostTitleById")),
            @Result(column = "author_id", property = "author", javaType = usyd.elec5619.topicoservice.model.User.class, one = @One(select = "usyd.elec5619.topicoservice.mapper.UserMapper.getUserById")),
            @Result(column = "content", property = "content"),
            @Result(column = "likes", property = "likes"),
            @Result(column = "dislikes", property = "dislikes"),
            @Result(column = "replies", property = "replies"),
            @Result(column = "ctime", property = "ctime"),
            @Result(column = "utime", property = "utime")
    })
    List<CommentVO> getCommentsByUserId(Long userId, Integer offset, Integer size);

    @Select("SELECT " +
            "id," +
            "post_id," +
            "author_id," +
            "content," +
            "likes," +
            "dislikes," +
            "replies," +
            "ctime," +
            "utime " +
            "FROM t_comment " +
            "WHERE post_id = #{postId} AND parent_id IS NULL " +
            "ORDER BY ctime DESC " +
            "LIMIT #{offset}, #{size}")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "post_id", property = "postId"),
            @Result(column = "post_id", property = "postTitle", javaType = String.class, one = @One(select = "usyd.elec5619.topicoservice.mapper.PostMapper.getPostTitleById")),
            @Result(column = "author_id", property = "author", javaType = usyd.elec5619.topicoservice.model.User.class, one = @One(select = "usyd.elec5619.topicoservice.mapper.UserMapper.getUserById")),
            @Result(column = "id", property = "children", javaType = List.class, many = @Many(select = "usyd.elec5619.topicoservice.mapper.CommentMapper.getRepliesByCommentId")),
            @Result(column = "content", property = "content"),
            @Result(column = "likes", property = "likes"),
            @Result(column = "dislikes", property = "dislikes"),
            @Result(column = "replies", property = "replies"),
            @Result(column = "ctime", property = "ctime"),
            @Result(column = "utime", property = "utime")
    })
    List<CommentVO> getNewCommentsByPostId(Long postId, Integer offset, Integer size);

    @Select("SELECT " +
            "id," +
            "post_id," +
            "author_id," +
            "content," +
            "likes," +
            "dislikes," +
            "replies," +
            "ctime," +
            "utime " +
            "FROM t_comment " +
            "WHERE post_id = #{postId} AND parent_id IS NULL " +
            "ORDER BY likes DESC " +
            "LIMIT #{offset}, #{size}")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "post_id", property = "postId"),
            @Result(column = "post_id", property = "postTitle", javaType = String.class, one = @One(select = "usyd.elec5619.topicoservice.mapper.PostMapper.getPostTitleById")),
            @Result(column = "author_id", property = "author", javaType = usyd.elec5619.topicoservice.model.User.class, one = @One(select = "usyd.elec5619.topicoservice.mapper.UserMapper.getUserById")),
            @Result(column = "id", property = "children", javaType = List.class, many = @Many(select = "usyd.elec5619.topicoservice.mapper.CommentMapper.getRepliesByCommentId")),
            @Result(column = "content", property = "content"),
            @Result(column = "likes", property = "likes"),
            @Result(column = "dislikes", property = "dislikes"),
            @Result(column = "replies", property = "replies"),
            @Result(column = "ctime", property = "ctime"),
            @Result(column = "utime", property = "utime")
    })
    List<CommentVO> getHotCommentsByPostId(Long postId, Integer offset, Integer size);

//    @Select("SELECT " +
//            "id," +
//            "post_id," +
//            "author_id," +
//            "content," +
//            "likes," +
//            "dislikes," +
//            "replies," +
//            "ctime," +
//            "utime " +
//            "FROM t_comment " +
//            "WHERE parent_id = #{commentId} " +
//            "ORDER BY `ctime` DESC " +
//            "LIMIT #{offset}, #{size}")
//    @Results({
//            @Result(column = "id", property = "id"),
//            @Result(column = "post_id", property = "postId"),
//            @Result(column = "post_id", property = "postTitle", javaType = String.class, one = @One(select = "usyd.elec5619.topicoservice.mapper.PostMapper.getPostTitleById")),
//            @Result(column = "author_id", property = "author", javaType = usyd.elec5619.topicoservice.model.User.class, one = @One(select = "usyd.elec5619.topicoservice.mapper.UserMapper.getUserById")),
//            @Result(column = "id", property = "children", javaType = List.class, many = @Many(select = "usyd.elec5619.topicoservice.mapper.CommentMapper.getRepliesByCommentId")),
//            @Result(column = "content", property = "content"),
//            @Result(column = "likes", property = "likes"),
//            @Result(column = "dislikes", property = "dislikes"),
//            @Result(column = "replies", property = "replies"),
//            @Result(column = "ctime", property = "ctime"),
//            @Result(column = "utime", property = "utime")
//    })
//    List<CommentVO> getRepliesByCommentId(Long commentId);
    @Select("SELECT * FROM t_comment WHERE parent_id = #{commentId} ORDER BY `ctime` DESC LIMIT #{offset}, #{size}")
    List<Comment> getRepliesByCommentId(Long commentId);

    @Insert("INSERT INTO t_comment " +
            "(post_id, author_id, parent_id, reply_to_user_id, content) " +
            "VALUES " +
            "(#{postId}, #{authorId}, #{parentId}, #{replyToUserId}, #{content})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    void insertOne(Comment comment);

//    @Select("SELECT " +
//            "id," +
//            "post_id," +
//            "author_id," +
//            "content," +
//            "likes," +
//            "dislikes," +
//            "replies," +
//            "ctime," +
//            "utime " +
//            "FROM t_comment " +
//            "WHERE id = #{id} " +
//            "LIMIT 1")
//    @Results({
//            @Result(column = "id", property = "id"),
//            @Result(column = "post_id", property = "postId"),
//            @Result(column = "post_id", property = "postTitle", javaType = String.class, one = @One(select = "usyd.elec5619.topicoservice.mapper.PostMapper.getPostTitleById")),
//            @Result(column = "author_id", property = "author", javaType = usyd.elec5619.topicoservice.model.User.class, one = @One(select = "usyd.elec5619.topicoservice.mapper.UserMapper.getUserById")),
//            @Result(column = "id", property = "children", javaType = List.class, many = @Many(select = "usyd.elec5619.topicoservice.mapper.CommentMapper.getRepliesByCommentId")),
//            @Result(column = "content", property = "content"),
//            @Result(column = "likes", property = "likes"),
//            @Result(column = "dislikes", property = "dislikes"),
//            @Result(column = "replies", property = "replies"),
//            @Result(column = "ctime", property = "ctime"),
//            @Result(column = "utime", property = "utime")
//    })
//    Optional<CommentVO> getCommentVOById(Long id);

    @Select("SELECT * FROM t_comment WHERE id = #{id} LIMIT 1")
    Optional<Comment> getCommentById(Long id);


//    @Select("Select * from t_comment where id = #{commentId} limit 1")
//    Optional<Comment> getCommentById(Long commentId);

    @Delete("DELETE FROM t_comment WHERE id = #{commentId}")
    void deleteOne(Long commentId);

    @Select("SELECT * FROM t_comment WHERE post_id = #{id}")
    List<Comment> getCommentsByPostId(Long id);

    @Delete("DELETE FROM t_user_like_comment WHERE comment_id = #{id}")
    void deleteAllLikesByCommentId(Long id);

    @Select("SELECT id FROM t_comment WHERE post_id = #{postId}")
    List<Long> getCommentIdsByPostId(Long postId);

    @Update("UPDATE t_comment SET replies = replies + 1 WHERE id = #{postId}")
    void addReplyToComment(Long commentId);
    @Update("UPDATE t_comment SET replies = CASE WHEN replies > 0 THEN replies - 1 ELSE 0 END WHERE id = #{postId}")
    void decrementReplyToComment(Long commentId);
}