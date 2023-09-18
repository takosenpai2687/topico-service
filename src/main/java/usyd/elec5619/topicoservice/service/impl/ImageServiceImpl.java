package usyd.elec5619.topicoservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import usyd.elec5619.topicoservice.mapper.ImageMapper;
import usyd.elec5619.topicoservice.model.Image;
import usyd.elec5619.topicoservice.service.ImageService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {

    private final ImageMapper imageMapper;

    @Override
    public Image uploadImage(String imageBase64) {
        final String uuid = imageMapper.insertImage(imageBase64);
        return imageMapper.getImageByUuid(uuid);
    }

    @Override
    public void addImagesToPost(Long postId, List<String> imgUuids) {
        for (String imageUuid : imgUuids) {
            if (!imageMapper.isImageExist(imageUuid))
                continue;
            if (!imageMapper.isImageInPost(imageUuid, postId))
                continue;
            imageMapper.addImageToPost(postId, imageUuid);
        }
    }
}
