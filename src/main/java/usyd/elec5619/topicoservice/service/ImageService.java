package usyd.elec5619.topicoservice.service;

import org.springframework.stereotype.Service;
import usyd.elec5619.topicoservice.model.Image;

import java.util.List;

@Service
public interface ImageService {

    Image uploadImage(String imageBase64);

    void addImagesToPost(Long postId, List<String> imgUuids);
}
