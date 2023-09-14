package usyd.elec5619.topicoservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import usyd.elec5619.topicoservice.mapper.ImageMapper;
import usyd.elec5619.topicoservice.service.ImageService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {

    private final ImageMapper imageMapper;

    @Override
    public String uploadImage(String image) {
        return null;
    }

    @Override
    public void addImagesToPost(Long postId, List<String> images) {
        for (String imageUuid : images) {
            if (imageMapper.isImageExist(imageUuid))
                continue;
            imageMapper.addImageToPost(postId, imageUuid);
        }
    }
}
