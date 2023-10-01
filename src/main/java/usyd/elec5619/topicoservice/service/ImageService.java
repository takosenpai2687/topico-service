package usyd.elec5619.topicoservice.service;

import org.apache.ibatis.annotations.Select;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import usyd.elec5619.topicoservice.model.Image;

import java.util.List;

@Service
public interface ImageService {

    ResponseEntity<byte[]> getImageById(Long id);

    Image uploadImage(MultipartFile imageFile);

    void addImagesToPost(Long postId, List<Long> imageIds);

    void deleteImagesByPostId(Long id);


    List<String> getImagesByPostId(Long postId);
}
