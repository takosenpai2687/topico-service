package usyd.elec5619.topicoservice.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Role {
    ROLE_USER,
    ROLE_ADMIN,
    ROLE_SUPER_ADMIN;
}
