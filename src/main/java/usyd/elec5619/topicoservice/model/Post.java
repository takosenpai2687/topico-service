package usyd.elec5619.topicoservice.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
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
    private LocalDateTime ctime;
    private LocalDateTime utime;
}