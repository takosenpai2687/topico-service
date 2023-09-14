package usyd.elec5619.topicoservice.service;

import org.springframework.stereotype.Service;
import usyd.elec5619.topicoservice.model.Community;

import java.util.List;

@Service
public interface CommunityService {
    Community getCommunityById(Long communityId);

    List<Community> getCommunitiesFollowedByUser(Long userId);

    List<Community> getCommunitiesRecommendedToUser(Long userId, Integer limit);

    Community getCommunityByPostId(Long postId);

    List<Community> searchByName(String keyword, Integer limit);
}
