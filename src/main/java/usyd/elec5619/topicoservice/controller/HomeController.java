package usyd.elec5619.topicoservice.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import usyd.elec5619.topicoservice.model.Community;
import usyd.elec5619.topicoservice.model.User;
import usyd.elec5619.topicoservice.pojo.CommonResponse;
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
    public CommonResponse<PostsVO> getMyPosts(Authentication authentication, @RequestParam(required = false, defaultValue = "0") Integer page, @RequestParam(required = false, defaultValue = "10") Integer size) {
        final String email = authentication.getName();
        final Long userId = userService.emailToId(email);
        PostsVO postsVO = postService.getPostsByUserId(userId, page, size);
        return CommonResponse.success(postsVO);
    }
}
