package usyd.elec5619.topicoservice.mapper;

import org.apache.ibatis.annotations.*;
import usyd.elec5619.topicoservice.model.Image;

@Mapper
public interface ImageMapper {

    @Insert("INSERT INTO t_image (uuid, base64) VALUES (UUID(), #{imageBase64})")
    @Options(useGeneratedKeys = true, keyProperty = "uuid", keyColumn = "uuid")
    String insertImage(String image);

    @Select("SELECT EXISTS(SELECT 1 FROM t_image WHERE uuid = #{imageUuid})")
    Boolean isImageExist(String imageUuid);


    @Insert("INSERT INTO t_post_image (post_id, image_uuid) VALUES (#{postId}, #{imageUuid})")
    void addImageToPost(Long postId, String imageUuid);

    @Select("SELECT EXISTS(SELECT 1 FROM t_post_image WHERE image_uuid = #{imageUuid} AND post_id = #{postId})")
    Boolean isImageInPost(String imageUuid, Long postId);

    @Select("SELECT * FROM t_image WHERE uuid = #{uuid}")
    Image getImageByUuid(String uuid);
}