package usyd.elec5619.topicoservice.mapper;

import org.apache.ibatis.annotations.*;
import usyd.elec5619.topicoservice.model.Image;

import java.util.Optional;

@Mapper
public interface ImageMapper {
    @Insert("INSERT INTO t_image (data, md5) VALUES(#{data}, #{md5})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    Long insert(Image image);

    @Select("SELECT * FROM t_image WHERE `id` = #{id}")
    Optional<Image> getById(Long id);

    @Select("SELECT * FROM t_image WHERE `md5` = #{md5}")
    Optional<Image> getByMd5(String md5);

    @Select("SELECT EXISTS(SELECT 1 FROM t_image WHERE `id` = #{id})")
    Boolean isImageExist(Long id);


    @Insert("INSERT INTO t_post_image (post_id, image_id) VALUES (#{postId}, #{imageId})")
    void addImageToPost(Long postId, Long imageId);

    @Select("SELECT EXISTS(SELECT 1 FROM t_post_image WHERE image_id = #{imageId} AND post_id = #{postId})")
    Boolean isImageInPost(Long imageId, Long postId);

}