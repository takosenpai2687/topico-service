package usyd.elec5619.topicoservice.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class Image {
    private Long id;
    private byte[] data;
    private LocalDateTime ctime;
    private LocalDateTime utime;
}