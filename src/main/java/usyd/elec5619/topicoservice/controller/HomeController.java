package usyd.elec5619.topicoservice.controller;

import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import usyd.elec5619.topicoservice.model.Community;
import usyd.elec5619.topicoservice.model.User;
import usyd.elec5619.topicoservice.pojo.CommonResponse;
import usyd.elec5619.topicoservice.service.CheckinService;
import usyd.elec5619.topicoservice.service.CommunityService;
import usyd.elec5619.topicoservice.service.PostService;
import usyd.elec5619.topicoservice.service.UserService;
import usyd.elec5619.topicoservice.vo.PostsVO;

import java.util.List;

@RestController("/api/v1/home")
public class HomeController {

    private UserService userService;
    private PostService postService;
    private CommunityService communityService;
    private CheckinService checkinService;

    @GetMapping("/communities_following")
    public CommonResponse<List<Community>> getCommunitiesFollowing(Authentication authentication) {
        final String email = authentication.getName();
        final Long userId = userService.emailToId(email);
        List<Community> communitiesFollowing = communityService.getCommunitiesFollowedByUser(userId);
        return CommonResponse.success(communitiesFollowing);
    }

    @GetMapping("/communities_recommended")
    public CommonResponse<List<Community>> getCommunitiesRecommended(Authentication authentication, @RequestParam(required = false, defaultValue = "5") Integer limit) {
        final String email = authentication.getName();
        final Long userId = userService.emailToId(email);
        List<Community> communitiesRecommended = communityService.getCommunitiesRecommendedToUser(userId, limit);
        return CommonResponse.success(communitiesRecommended);
    }

    @GetMapping("/me")
    public CommonResponse<User> getMe(Authentication authentication) {
        final String email = authentication.getName();
        User user = userService.getUserByEmail(email);
        return CommonResponse.success(user);
    }

    @GetMapping("/my_posts")
    public CommonResponse<PostsVO> getMyPosts(Authentication authentication, @Valid @RequestParam(required = false, defaultValue = "0") Integer page, @Valid @RequestParam(required = false, defaultValue = "10") Integer size) {
        final String email = authentication.getName();
        final Long userId = userService.emailToId(email);
        PostsVO postsVO = postService.getPostsByUserId(userId, page, size);
        return CommonResponse.success(postsVO);
    }

    @PostMapping("/checkin/{communityId}")
    public CommonResponse<Void> checkin(Authentication authentication, @Valid @PathVariable Long communityId) {
        final String email = authentication.getName();
        final Long userId = userService.emailToId(email);
        checkinService.checkin(userId, communityId);
        return CommonResponse.success();
    }

    @PostMapping("/checkin")
    public CommonResponse<Void> checkinForAll(Authentication authentication) {
        final String email = authentication.getName();
        final Long userId = userService.emailToId(email);
        checkinService.checkinForAll(userId);
        return CommonResponse.success();
    }

}
