package usyd.elec5619.topicoservice.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class Image {
    private String uuid;
    private String base64;
    private LocalDateTime ctime;
    private LocalDateTime utime;
}