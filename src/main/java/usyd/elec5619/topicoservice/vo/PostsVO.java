package usyd.elec5619.topicoservice.vo;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import java.util.List;

@Data
@Builder
public class PostsVO {
    @NonNull List<PostVO> posts;
    @NonNull Integer total;
    @NonNull Integer page;
    @NonNull Integer size;
}
