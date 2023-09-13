package usyd.elec5619.topicoservice.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import usyd.elec5619.topicoservice.model.Post;

import java.util.List;

@Mapper
public interface PostMapper {
    @Select("SELECT * FROM t_post WHERE author_id = #{userId} ORDER BY ctime DESC LIMIT #{offset}, #{size}")
    List<Post> getPostsByUserId(Long userId, Integer offset, Integer size);

    @Select("SELECT COUNT(*) FROM t_post WHERE author_id = #{userId}")
    Integer getTotalPostsByUserId(Long userId);
}