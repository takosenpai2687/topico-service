package usyd.elec5619.topicoservice.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import usyd.elec5619.topicoservice.dto.image.UploadImageDto;
import usyd.elec5619.topicoservice.model.Image;
import usyd.elec5619.topicoservice.pojo.CommonResponse;
import usyd.elec5619.topicoservice.service.ImageService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/images")
public class ImageController {

    private final ImageService imageService;

    @PostMapping("/")
    public CommonResponse<Image> uploadImage(@RequestBody @Valid UploadImageDto uploadImageDto) {
        final String imageBase64 = uploadImageDto.getImageBase64();
        final Image img = imageService.uploadImage(imageBase64);
        return CommonResponse.success(img);
    }

}
