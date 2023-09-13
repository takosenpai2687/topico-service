package usyd.elec5619.topicoservice.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BrowseHistory {
    private Long id;
    private Long userId;
    private Long postId;
    private LocalDateTime ctime;
    private LocalDateTime utime;

}