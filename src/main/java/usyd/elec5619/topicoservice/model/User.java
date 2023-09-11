package usyd.elec5619.topicoservice.model;

import lombok.Builder;
import lombok.Data;
import usyd.elec5619.topicoservice.type.Gender;

import java.time.LocalDateTime;

@Data
@Builder
public class User {
    private Integer id;
    private String email;
    private String nickName;
    private String password;
    private Gender gender;
    private String location;
    private String avatar;
    private String description;
    private LocalDateTime ctime;
    private LocalDateTime utime;
}