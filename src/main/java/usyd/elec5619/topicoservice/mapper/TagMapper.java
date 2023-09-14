package usyd.elec5619.topicoservice.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import usyd.elec5619.topicoservice.model.Tag;

import java.util.List;

@Mapper
public interface TagMapper {

    @Select("SELECT t.id, t.name " +
            "FROM t_tag t " +
            "JOIN t_post_tag pt ON t.id = pt.tag_id " +
            "WHERE pt.post_id = #{postId}")
    List<Tag> getTagsByPostId(Long postId);
}