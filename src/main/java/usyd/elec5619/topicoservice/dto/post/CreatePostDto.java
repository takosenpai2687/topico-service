package usyd.elec5619.topicoservice.dto.post;

import jakarta.annotation.Nonnull;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CreatePostDto {
    @Nonnull
    private Long communityId;
    @Nonnull
    private String title;
    @Nonnull
    private String content;
    @Nonnull
    private Boolean spoiler;
    private List<String> images;
    private String tags;
}
