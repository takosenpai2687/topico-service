package usyd.elec5619.topicoservice.dto.post;

import jakarta.annotation.Nonnull;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CreatePostDto {
    @Nonnull
    private Long communityId;
    @NotBlank
    private String title;
    @NotBlank
    private String content;
    @Nonnull
    private Boolean spoiler;
    private List<String> images;
    private String tags;
}
