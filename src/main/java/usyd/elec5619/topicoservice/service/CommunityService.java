package usyd.elec5619.topicoservice.service;

import org.springframework.stereotype.Service;
import usyd.elec5619.topicoservice.model.Community;
import usyd.elec5619.topicoservice.model.UserCommunity;

import java.util.List;

@Service
public interface CommunityService {
    Community getCommunityById(Long communityId);

    List<Community> getCommunitiesFollowedByUser(Long userId);

    List<Community> getCommunitiesRecommendedToUser(Long userId, Integer limit);

    Community getCommunityByPostId(Long postId);

    List<Community> searchByKeyword(String keyword, Integer limit);

    UserCommunity getUserCommunity(Long userId, Long communityId);

    UserCommunity follow(Long userId, Long communityId);

    UserCommunity unfollow(Long userId, Long communityId);
}
