package usyd.elec5619.topicoservice.model;

import lombok.*;

import java.time.LocalDateTime;

@Data
public class Image {
    private String uuid;
    private String path;
    private LocalDateTime ctime;
    private LocalDateTime utime;
}