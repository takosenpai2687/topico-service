package usyd.elec5619.topicoservice.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import usyd.elec5619.topicoservice.exception.http.NotFoundException;
import usyd.elec5619.topicoservice.model.Community;
import usyd.elec5619.topicoservice.model.UserCommunity;
import usyd.elec5619.topicoservice.pojo.CommonResponse;
import usyd.elec5619.topicoservice.service.CheckinService;
import usyd.elec5619.topicoservice.service.CommunityService;
import usyd.elec5619.topicoservice.service.PostService;
import usyd.elec5619.topicoservice.service.UserService;
import usyd.elec5619.topicoservice.type.SortBy;
import usyd.elec5619.topicoservice.util.LevelUtil;
import usyd.elec5619.topicoservice.vo.CheckinVO;
import usyd.elec5619.topicoservice.vo.Pager;
import usyd.elec5619.topicoservice.vo.PostVO;

@RestController()
@RequestMapping("/api/v1/communities")
@RequiredArgsConstructor
@Slf4j
public class CommunityController {

    private final UserService userService;
    private final CheckinService checkinService;
    private final CommunityService communityService;
    private final PostService postService;

    @GetMapping("/{communityId}")
    public CommonResponse<Community> getCommunity(@Valid @PathVariable Long communityId) {
        final Community community = communityService.getCommunity(communityId);
        if (community == null)
            throw new NotFoundException("Community not found");
        return CommonResponse.success(community);
    }

    @GetMapping("/my/{communityId}")
    public CommonResponse<UserCommunity> getUserCommunity(Authentication authentication, @Valid @PathVariable Long communityId) {
        final String email = authentication.getName();
        final Long userId = userService.emailToId(email);
        UserCommunity userCommunity = communityService.getUserCommunity(userId, communityId);
        return CommonResponse.success(userCommunity);
    }

    @GetMapping("/checkin/{communityId}")
    public CommonResponse<CheckinVO> getCheckin(Authentication authentication, @Valid @PathVariable Long communityId) {
        final String email = authentication.getName();
        final Long userId = userService.emailToId(email);
        final CheckinVO checkinVO = checkinService.getCheckin(userId, communityId);
        return CommonResponse.success(checkinVO);
    }

    @PostMapping("/checkin/{communityId}")
    public CommonResponse<UserCommunity> checkin(Authentication authentication, @Valid @PathVariable Long communityId) {
        final String email = authentication.getName();
        final Long userId = userService.emailToId(email);
        checkinService.checkin(userId, communityId);
        UserCommunity userCommunity = communityService.getUserCommunity(userId, communityId);
        return CommonResponse.success(userCommunity);
    }

    @PostMapping("/follow/{communityId}")
    public CommonResponse<UserCommunity> follow(Authentication authentication, @Valid @PathVariable Long communityId) {
        final String email = authentication.getName();
        final Long userId = userService.emailToId(email);
        final UserCommunity userCommunity = communityService.follow(userId, communityId);
        return CommonResponse.success(userCommunity);
    }

    @DeleteMapping("/follow/{communityId}")
    public CommonResponse<Void> unfollow(Authentication authentication, @Valid @PathVariable Long communityId) {
        final String email = authentication.getName();
        final Long userId = userService.emailToId(email);
        communityService.unfollow(userId, communityId);
        return CommonResponse.success();
    }

    @GetMapping("/community_posts/{communityId}")
    public CommonResponse<Pager<PostVO>> getCommunityPosts(@PathVariable Long communityId, @RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer size, @RequestParam(defaultValue = "MOST_LIKES") SortBy sortBy) {
        final Pager<PostVO> posts = postService.getPostsByCommunityId(communityId, page, size, sortBy);
        log.info("getting trending posts: {}", posts);
        return CommonResponse.success(posts);
    }

    @GetMapping("/rank_by_size/{communityId}")
    public CommonResponse<Integer> getRankBySize(@PathVariable Long communityId) {
        final Integer rank = communityService.getRankBySize(communityId);
        return CommonResponse.success(rank);
    }

    @GetMapping("/next_exp")
    public CommonResponse<Integer> getNextExp(@RequestParam("level") Integer level) {
        final Integer nextExp = LevelUtil.maxExpAtLevel(level);
        return CommonResponse.success(nextExp);
    }
}
