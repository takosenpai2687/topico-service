package usyd.elec5619.topicoservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import usyd.elec5619.topicoservice.exception.http.BadRequestException;
import usyd.elec5619.topicoservice.exception.http.InternalException;
import usyd.elec5619.topicoservice.exception.http.NotFoundException;
import usyd.elec5619.topicoservice.mapper.ImageMapper;
import usyd.elec5619.topicoservice.model.Image;
import usyd.elec5619.topicoservice.service.ImageService;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {

    private final ImageMapper imageMapper;

    @Override
    public byte[] getImageById(Long id) {
        return imageMapper.getById(id).orElseThrow(() -> new NotFoundException("Image not found")).getData();
    }

    @Override
    public Image uploadImage(MultipartFile imageFile) {
        try {
            final byte[] data = imageFile.getBytes();
            final Image image = Image.builder().data(data).build();
            Long imageId = imageMapper.insert(image);
            return imageMapper.getById(imageId).orElseThrow(() -> new InternalException("Failed to upload image"));
        } catch (IOException e) {
            throw new BadRequestException(e.getMessage());
        }
    }

    @Override
    public void addImagesToPost(Long postId, List<Long> imageIds) {
        for (Long imageId : imageIds) {
            if (!imageMapper.isImageExist(imageId)) continue;
            if (!imageMapper.isImageInPost(imageId, postId)) continue;
            imageMapper.addImageToPost(postId, imageId);
        }
    }
}
