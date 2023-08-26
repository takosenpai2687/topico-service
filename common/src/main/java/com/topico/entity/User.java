package com.topico.entity;


import com.topico.enums.Gender;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User extends BaseEntity implements UserDetails {

    @NotBlank
    @Column(unique = true, nullable = false)
    private String email;

    @NotBlank
    @Column(name = "nick_name", nullable = false, unique = true)
    private String nickName;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, columnDefinition = "TINYINT default 0")
    @Enumerated(EnumType.ORDINAL)
    private Gender gender;

    @Column(nullable = true)
    private Integer age;

    @Column(nullable = true)
    private String location;

    @Column(nullable = true)
    private String avatar;

    @Column(nullable = true, columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false, columnDefinition = "boolean default false")
    private Boolean deleted;

    // Subscribed communities
    @ManyToMany
    @JoinTable(name = "user_community_subscription", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "community_id"))
    private Set<Community> subscribedCommunities = new HashSet<>();

    // Check in
    @OneToMany(mappedBy = "user")
    private Set<CommunityCheckin> communityCheckins = new HashSet<>();

    // Roles
    @OneToMany(mappedBy = "user")
    private Set<UserCommunityRole> userCommunityRoles = new HashSet<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> authorities = new HashSet<>();
        for (UserCommunityRole userCommunityRole : this.userCommunityRoles) {
            authorities.add(userCommunityRole.getRole());
        }
        return authorities;
    }

    @Override
    public String getUsername() {
        return this.nickName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return !deleted;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !deleted;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return !deleted;
    }

    @Override
    public boolean isEnabled() {
        return !deleted;
    }
}
