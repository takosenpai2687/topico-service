package usyd.elec5619.topicoservice.dto.image;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UploadImageDto {
    @NotBlank
    private String imageBase64;
}
