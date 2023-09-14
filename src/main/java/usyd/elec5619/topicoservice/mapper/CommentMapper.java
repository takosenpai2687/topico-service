package usyd.elec5619.topicoservice.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface CommentMapper {

    @Select("SELECT COUNT(id) FROM t_comment WHERE post_id = #{id}")
    Integer countCommentsByPostId(Long id);

}