package usyd.elec5619.topicoservice.dto.user;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UpdatePasswordDto {
    @NotBlank
    private String oldPassword;
    @NotBlank
    private String newPassword;
}
