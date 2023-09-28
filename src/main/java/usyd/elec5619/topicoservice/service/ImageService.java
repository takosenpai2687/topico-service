package usyd.elec5619.topicoservice.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import usyd.elec5619.topicoservice.model.Image;

import java.util.List;

@Service
public interface ImageService {

    byte[] getImageById(Long id);

    Image uploadImage(MultipartFile imageFile);

    void addImagesToPost(Long postId, List<Long> imageIds);
}
