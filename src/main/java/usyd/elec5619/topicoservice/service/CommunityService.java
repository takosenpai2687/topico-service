package usyd.elec5619.topicoservice.service;

import usyd.elec5619.topicoservice.model.Community;

import java.util.List;

public interface CommunityService {
    Community getCommunityById(Long communityId);

    List<Community> getCommunitiesFollowedByUser(Long userId);

    List<Community> getCommunitiesRecommendedToUser(Long userId, Integer limit);
}
