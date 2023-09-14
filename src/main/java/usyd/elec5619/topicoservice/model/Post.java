package usyd.elec5619.topicoservice.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class Post {
    private Long id;
    private Long communityId;
    private Long authorId;
    private String title;
    private String content;
    private Boolean spoiler;
    private Integer likes;
    private Integer dislikes;
    private Integer replies;
    private String tags;
    private LocalDateTime ctime;
    private LocalDateTime utime;
}