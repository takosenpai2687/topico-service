package usyd.elec5619.topicoservice.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Community {
    private Long id;
    private String name;
    private LocalDateTime ctime;
    private LocalDateTime utime;
    private String description;
}