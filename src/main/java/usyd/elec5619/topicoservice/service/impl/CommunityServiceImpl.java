package usyd.elec5619.topicoservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import usyd.elec5619.topicoservice.mapper.CommunityMapper;
import usyd.elec5619.topicoservice.model.Community;
import usyd.elec5619.topicoservice.service.CommunityService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommunityServiceImpl implements CommunityService {

    private final CommunityMapper communityMapper;

    @Override
    public Community getCommunityById(Long communityId) {
        return communityMapper.getCommunityById(communityId);
    }

    @Override
    public List<Community> getCommunitiesFollowedByUser(Long userId) {
        return communityMapper.getCommunitiesFollowedByUser(userId);
    }

    @Override
    public List<Community> getCommunitiesRecommendedToUser(Long userId, Integer limit) {
        return communityMapper.getCommunitiesRecommendedToUser(userId, limit);
    }

    @Override
    public Community getCommunityByPostId(Long postId) {
        return communityMapper.getCommunityByPostId(postId);
    }
}
