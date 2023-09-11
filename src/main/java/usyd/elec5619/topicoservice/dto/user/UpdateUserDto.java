package usyd.elec5619.topicoservice.dto.user;

import jakarta.annotation.Nullable;
import usyd.elec5619.topicoservice.type.Gender;

public class UpdateUserDto {

    @Nullable
    Long id;

    @Nullable
    String nickName;

    @Nullable
    String password;

    @Nullable
    Gender gender;

    @Nullable
    String location;

    @Nullable
    String avatar;

    @Nullable
    String description;

}
