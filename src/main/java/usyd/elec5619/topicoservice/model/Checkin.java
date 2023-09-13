package usyd.elec5619.topicoservice.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Checkin {
    private Long id;
    private Long userId;
    private Long communityId;
    private Integer accumulatedDays;
    private LocalDateTime ctime;
    private LocalDateTime utime;

}