package usyd.elec5619.topicoservice.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginDto {

    @Email(message = "Email format is incorrect")
    private String email;

    @NotBlank(message = "Password cannot be blank")
    private String password;
    
}
