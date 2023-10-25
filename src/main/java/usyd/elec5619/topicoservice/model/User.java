package usyd.elec5619.topicoservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private Long id;
    private String email;
    private String nickName;
    @JsonIgnore
    private String password;
    private Gender gender;
    private String location;
    private Long avatar;
    private String description;
    private Role role;
    private LocalDateTime ctime;
    @JsonIgnore
    private LocalDateTime utime;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername() {
        // Use id as username
        return id.toString();
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