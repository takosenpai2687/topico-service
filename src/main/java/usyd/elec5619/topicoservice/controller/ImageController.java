package usyd.elec5619.topicoservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import usyd.elec5619.topicoservice.model.Image;
import usyd.elec5619.topicoservice.pojo.CommonResponse;
import usyd.elec5619.topicoservice.service.ImageService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/images")
public class ImageController {

    private final ImageService imageService;

    @PostMapping(headers = {"content-type=multipart/form-data"})
    public CommonResponse<?> uploadImage(@RequestParam("image") MultipartFile file) {
        final Image img = imageService.uploadImage(file);
        return CommonResponse.success(img);
    }

    @GetMapping("/{id}")
    public ResponseEntity<byte[]> getImageById(@PathVariable("id") Long id) {
        return imageService.getImageById(id);
    }

}
