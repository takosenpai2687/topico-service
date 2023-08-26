package com.topico.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Community extends BaseEntity {
    @Column(nullable = false, unique = true)
    private String name;

    private String description;

    @OneToMany(mappedBy = "community")
    private List<UserCommunityRole> userCommunityRoles = new ArrayList<>();

    @ManyToMany(mappedBy = "subscribedCommunities")
    private Set<User> subscribers = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "community_tags",
            joinColumns = @JoinColumn(name = "community_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private Set<Tag> tags = new HashSet<>();

    @Column(nullable = false, columnDefinition = "boolean default false")
    private Boolean unread = false;
}
