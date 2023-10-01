package usyd.elec5619.topicoservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import usyd.elec5619.topicoservice.dto.community.CreateCommunityDto;
import usyd.elec5619.topicoservice.dto.community.UpdateCommunityDto;
import usyd.elec5619.topicoservice.exception.http.BadRequestException;
import usyd.elec5619.topicoservice.exception.http.NotFoundException;
import usyd.elec5619.topicoservice.mapper.CommunityMapper;
import usyd.elec5619.topicoservice.model.Community;
import usyd.elec5619.topicoservice.model.UserCommunity;
import usyd.elec5619.topicoservice.service.CommunityService;
import usyd.elec5619.topicoservice.service.PostService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommunityServiceImpl implements CommunityService {

    private final CommunityMapper communityMapper;

    private final PostService postService;

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
        final String communityName = createCommunityDto.getName();
        if (communityMapper.getCommunityByName(communityName).isPresent()) {
            throw new BadRequestException("Community name already exists");
        }
        Community community = Community.builder()
                                       .name(createCommunityDto.getName())
                                       .description(createCommunityDto.getDescription())
                                       .followers(createCommunityDto.getFollowers())
                                       .avatar(createCommunityDto.getAvatar())
                                       .banner(createCommunityDto.getBanner())
                                       .build();
        communityMapper.insertOne(community);
        Long id = community.getId();  //fix the id problem that always return 1: Long id = communityMapper.insertOne(community);
        return communityMapper.getCommunityById(id);

    }

    @Override
    public Community updateCommunity(Long communityId, UpdateCommunityDto updateCommunityDto) {
        // TODO: update community
        Community existingCommunity = communityMapper.getCommunityById(communityId);
        if (existingCommunity == null) {
            throw new NotFoundException("Community not found");
        }
        if (updateCommunityDto.getName() != null) existingCommunity.setName(updateCommunityDto.getName());
        if (updateCommunityDto.getDescription() != null)
            existingCommunity.setDescription(updateCommunityDto.getDescription());
        if (updateCommunityDto.getAvatar() != null) existingCommunity.setAvatar(updateCommunityDto.getAvatar());
        if (updateCommunityDto.getBanner() != null) existingCommunity.setBanner(updateCommunityDto.getBanner());

        communityMapper.updateCommunity(communityId, updateCommunityDto);

        return communityMapper.getCommunityById(communityId);
    }

    @Override
    @Transactional
    public void deleteCommunity(Long communityId) {
        postService.deletePostsByCommunityId(communityId);
        communityMapper.unfollowCommunityForAllUsers(communityId);
    }
}
