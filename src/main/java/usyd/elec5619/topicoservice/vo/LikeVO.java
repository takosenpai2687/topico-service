package usyd.elec5619.topicoservice.vo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LikeVO {
    private Boolean liked;
    private Boolean disliked;
    private Integer likes;
    private Integer dislikes;
}
