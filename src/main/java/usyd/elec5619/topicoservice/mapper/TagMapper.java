package usyd.elec5619.topicoservice.mapper;

import org.apache.ibatis.annotations.*;
import usyd.elec5619.topicoservice.model.Tag;

import java.util.List;

@Mapper
public interface TagMapper {

    @Select("SELECT t.id, t.name " +
            "FROM t_tag t " +
            "JOIN t_post_tag pt ON t.id = pt.tag_id " +
            "WHERE pt.post_id = #{postId}")
    List<Tag> getTagsByPostId(Long postId);

    @Select("SELECT t.id, t.name " +
            "FROM t_tag t " +
            "JOIN t_community_tag ct ON t.id = ct.tag_id " +
            "WHERE ct.community_id = #{communityId}")
    List<Tag> getTagsByCommunityId(Long communityId);


    @Delete("DELETE FROM t_post_tag WHERE post_id = #{postId}")
    void deleteTagsByPostId(Long postId);

    @Insert("<script>" +
            "INSERT INTO t_post_tag (post_id, tag_id) VALUES " +
            "<foreach collection='tags' item='tag' separator=','>" +
            "(#{postId}, (SELECT id FROM t_tag WHERE name = #{tag}))" +
            "</foreach>" +
            "</script>")
    void addTagsToPost(Long postId, List<String> tags);

    @Select("SELECT * FROM t_tag WHERE name = #{tag}")
    Tag getTagByName(String tag);

    @Insert("INSERT INTO t_tag (name) VALUES (#{tag})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    Long createTag(String tag);


    @Insert("INSERT INTO t_post_tag (post_id, tag_id) " +
            "VALUES (#{postId}, #{tagId})")
    void addTagToPost(Long postId, Long tagId);
}