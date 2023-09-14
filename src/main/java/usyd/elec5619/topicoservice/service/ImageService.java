package usyd.elec5619.topicoservice.service;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ImageService {

    String uploadImage(String image);

    void addImagesToPost(Long postId, List<String> images);
}
