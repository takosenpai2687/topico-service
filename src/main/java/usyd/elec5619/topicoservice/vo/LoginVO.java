package usyd.elec5619.topicoservice.vo;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginVO {
    @NotBlank
    private String email;
    @NotBlank
    private String nickName;
    @NotBlank
    private String token;
}
