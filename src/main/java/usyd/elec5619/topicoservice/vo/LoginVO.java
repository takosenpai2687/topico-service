package usyd.elec5619.topicoservice.vo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
public class LoginVO {
    @NotNull
    private Long id;
    @NotBlank
    private String email;
    @NotBlank
    private String nickName;
    @NotBlank
    private String token;
}
