package usyd.elec5619.topicoservice.dto.user;

import jakarta.annotation.Nullable;
import lombok.Builder;
import lombok.Data;
import usyd.elec5619.topicoservice.type.Gender;

@Data
@Builder
public class UpdateUserDto {

    @Nullable
    String nickName;

    @Nullable
    Gender gender;

    @Nullable
    Long avatar;

    @Nullable
    String description;

}
