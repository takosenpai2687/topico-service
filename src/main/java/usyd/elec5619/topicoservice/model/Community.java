package usyd.elec5619.topicoservice.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class Community {
    private Long id;
    private String name;
    private String description;
    private Integer followers;
    private Long avatar;
    private Long banner;
    private String tags;
    private LocalDateTime ctime;
    private LocalDateTime utime;
}