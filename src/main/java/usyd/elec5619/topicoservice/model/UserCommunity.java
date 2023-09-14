package usyd.elec5619.topicoservice.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserCommunity {
    private Long id;
    private Long userId;
    private Long communityId;
    private Integer level;
    private Integer exp;
    private Integer checkin;
    private LocalDateTime ctime;
    private LocalDateTime utime;
}