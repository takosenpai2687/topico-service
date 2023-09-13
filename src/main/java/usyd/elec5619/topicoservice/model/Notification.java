package usyd.elec5619.topicoservice.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Notification {
    private Long id;
    private Byte type;
    private Long senderId;
    private Long receiverId;
    private Boolean unread;
    private LocalDateTime ctime;
    private LocalDateTime utime;
    private String content;
}