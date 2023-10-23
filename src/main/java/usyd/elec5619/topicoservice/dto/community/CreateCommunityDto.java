package usyd.elec5619.topicoservice.dto.community;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateCommunityDto {
    @NotBlank
    private String name;
    @NotBlank
    private String description;

    private Long avatar;
    private Long banner;
}
