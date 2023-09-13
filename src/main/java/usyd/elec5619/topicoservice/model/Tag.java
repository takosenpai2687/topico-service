package usyd.elec5619.topicoservice.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Tag {
    private Long id;
    private String name;
    private LocalDateTime ctime;
    private LocalDateTime utime;

}