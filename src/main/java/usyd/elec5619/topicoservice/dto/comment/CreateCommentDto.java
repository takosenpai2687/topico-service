package usyd.elec5619.topicoservice.dto.comment;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateCommentDto {
    @Nonnull
    private Long postId;
    @NotBlank
    private String content;
    @Nullable
    private Long parentId;
    private Long replyToUserId;
}
