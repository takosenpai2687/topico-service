package usyd.elec5619.topicoservice.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Community {
    private Long id;
    private String name;
    private String description;
    private Integer followers;
    private String avatar;
    private String banner;
    private LocalDateTime ctime;
    private LocalDateTime utime;
}