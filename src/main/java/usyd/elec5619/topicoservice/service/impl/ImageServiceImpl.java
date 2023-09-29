package usyd.elec5619.topicoservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import usyd.elec5619.topicoservice.exception.http.BadRequestException;
import usyd.elec5619.topicoservice.exception.http.InternalException;
import usyd.elec5619.topicoservice.exception.http.NotFoundException;
import usyd.elec5619.topicoservice.mapper.ImageMapper;
import usyd.elec5619.topicoservice.model.Image;
import usyd.elec5619.topicoservice.service.ImageService;
import usyd.elec5619.topicoservice.util.ImageUtil;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {

    private final ImageMapper imageMapper;

    @Override
    public ResponseEntity<byte[]> getImageById(Long id) {
        final Image img = imageMapper.getById(id).orElseThrow(() -> new NotFoundException("Image not found"));
        final byte[] imgData = img.getData();
        final String ext = img.getExt();
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, "image/" + ext).body(imgData);
    }

    @Override
    public Image uploadImage(MultipartFile imageFile) {
        try {
            // Extract image data
            final String ext = ImageUtil.getExt(imageFile);
            final byte[] data = imageFile.getBytes();

            // Check existing data
            final String md5 = ImageUtil.md5(data);
            final Optional<Image> dbImage = imageMapper.getByMd5(md5);
            if (dbImage.isPresent()) return dbImage.get();

            // Insert new image
            final Image image = Image.builder().data(data).md5(md5).ext(ext).build();
            Long imageId = imageMapper.insert(image);

            // Return created image
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
