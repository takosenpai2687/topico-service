package usyd.elec5619.topicoservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import java.time.LocalDateTime;

@Data
@Builder
public class Image {
    private Long id;
    @JsonIgnore
    private byte[] data;
    @NotBlank
    private String md5;
    private LocalDateTime ctime;
    private LocalDateTime utime;
}