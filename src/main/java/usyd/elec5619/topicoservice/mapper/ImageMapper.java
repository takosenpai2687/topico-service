package usyd.elec5619.topicoservice.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ImageMapper {

    @Select("SELECT EXISTS(SELECT 1 FROM t_image WHERE uuid = #{imageUuid})")
    Boolean isImageExist(String imageUuid);


    @Insert("INSERT INTO t_post_image (post_id, image_uuid) VALUES (#{postId}, #{imageUuid})")
    void addImageToPost(Long postId, String imageUuid);

    @Select("SELECT EXISTS(SELECT 1 FROM t_post_image WHERE image_uuid = #{imageUuid} AND post_id = #{postId})")
    Boolean isImageInPost(String imageUuid, Long postId);
}