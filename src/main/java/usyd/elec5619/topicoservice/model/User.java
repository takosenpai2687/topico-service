package usyd.elec5619.topicoservice.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import usyd.elec5619.topicoservice.type.Gender;
import usyd.elec5619.topicoservice.type.Role;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;

@Data
@Builder
public class User implements UserDetails {
    private Integer id;
    private String email;
    private String nickName;
    private String password;
    private Gender gender;
    private String location;
    private String avatar;
    private String description;
    private Role role;
    private LocalDateTime ctime;
    private LocalDateTime utime;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername() {
        // We use email as the username for Spring Security 6
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}