package usyd.elec5619.topicoservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import usyd.elec5619.topicoservice.dto.community.CreateCommunityDto;
import usyd.elec5619.topicoservice.dto.community.UpdateCommunityDto;
import usyd.elec5619.topicoservice.exception.http.BadRequestException;
import usyd.elec5619.topicoservice.mapper.CommunityMapper;
import usyd.elec5619.topicoservice.model.Community;
import usyd.elec5619.topicoservice.model.UserCommunity;
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

    @Override
    public List<Community> searchByKeyword(String keyword, Integer limit) {
        return communityMapper.searchByKeyword(keyword, limit);
    }

    @Override
    public UserCommunity getUserCommunity(Long userId, Long communityId) {
        return communityMapper.getUserCommunity(userId, communityId);
    }

    @Override
    public UserCommunity follow(Long userId, Long communityId) {
        if (communityMapper.isUserFollowingCommunity(userId, communityId)) {
            throw new BadRequestException("User has already followed this community");
        }
        communityMapper.follow(userId, communityId);
        return communityMapper.getUserCommunity(userId, communityId);
    }

    @Override
    public void unfollow(Long userId, Long communityId) {
        if (!communityMapper.isUserFollowingCommunity(userId, communityId)) {
            throw new BadRequestException("User has not followed this community");
        }
        communityMapper.unfollow(userId, communityId);
    }

    @Override
    public List<Community> getTopCommunities() {
        return communityMapper.getTopCommunities();
    }

    @Override
    public Community createCommunity(CreateCommunityDto createCommunityDto) {
        // TODO: create community
        return null;
    }

    @Override
    public Community updateCommunity(Long communityId, UpdateCommunityDto updateCommunityDto) {
        // TODO: update community
        return null;
    }

    @Override
    public void deleteCommunity(Long communityId) {
        // TODO: delete community
    }
}
