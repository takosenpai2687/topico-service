package com.topico.entity;


import com.topico.enums.Gender;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User extends BaseEntity {

    @NotBlank
    @Column(unique = true, nullable = false)
    private String email;

    @NotBlank
    @Column(name = "nick_name", nullable = false, unique = true)
    private String nickName;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, columnDefinition = "TINYINT default 0")
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


}
