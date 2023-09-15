package usyd.elec5619.topicoservice.dto.post;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CreatePostDto {
    private Long communityId;
    private String title;
    private String content;
    private Boolean spoiler;
    private List<String> images;
    private String tags;
}
