package usyd.elec5619.topicoservice.service;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ImageService {

    String uploadImage(String imageBase64);

    void addImagesToPost(Long postId, List<String> imgUuids);
}
