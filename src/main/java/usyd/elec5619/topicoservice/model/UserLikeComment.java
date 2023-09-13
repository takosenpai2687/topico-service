package usyd.elec5619.topicoservice.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserLikeComment {
    private Long id;
    private Long userId;
    private Long authorId;
    private Long commentId;
    private Boolean like;
    private LocalDateTime ctime;
    private LocalDateTime utime;
}