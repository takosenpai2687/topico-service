package usyd.elec5619.topicoservice.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import usyd.elec5619.topicoservice.mapper.UserMapper;
import usyd.elec5619.topicoservice.model.UserCommunity;
import usyd.elec5619.topicoservice.pojo.CommonResponse;
import usyd.elec5619.topicoservice.service.CheckinService;
import usyd.elec5619.topicoservice.service.CommunityService;
import usyd.elec5619.topicoservice.service.PostService;
import usyd.elec5619.topicoservice.service.UserService;
import usyd.elec5619.topicoservice.type.SortBy;
import usyd.elec5619.topicoservice.vo.Pager;
import usyd.elec5619.topicoservice.vo.PostVO;

@RestController()
@RequestMapping("/api/v1/communities")
@RequiredArgsConstructor
public class CommunityController {

    private final UserService userService;
    private final CheckinService checkinService;
    private final CommunityService communityService;
    private final PostService postService;

    @GetMapping("/{communityId}")
    public CommonResponse<UserCommunity> getCommunity(Authentication authentication, @Valid @PathVariable Long communityId) {
        final String email = authentication.getName();
        final Long userId = userService.emailToId(email);
        UserCommunity userCommunity = communityService.getUserCommunity(userId, communityId);
        return CommonResponse.success(userCommunity);
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
        return CommonResponse.success(posts);
    }
}
