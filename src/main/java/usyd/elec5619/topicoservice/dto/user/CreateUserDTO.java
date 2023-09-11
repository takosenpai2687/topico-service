package usyd.elec5619.topicoservice.dto.user;

import lombok.Data;

@Data
public class CreateUserDTO {
    private String email;
    private String password;
    private String nickName;
}
