package usyd.elec5619.topicoservice.controller;

import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import usyd.elec5619.topicoservice.model.Community;
import usyd.elec5619.topicoservice.model.User;
import usyd.elec5619.topicoservice.pojo.CommonResponse;
import usyd.elec5619.topicoservice.service.*;
import usyd.elec5619.topicoservice.vo.CommentVO;
import usyd.elec5619.topicoservice.vo.Pager;
import usyd.elec5619.topicoservice.vo.PostVO;

import java.util.List;

@RestController()
@RequestMapping("/api/v1/home")
public class HomeController {

    private UserService userService;
    private PostService postService;
    private CommunityService communityService;
    private CheckinService checkinService;
    private CommentService commentService;

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
    public CommonResponse<Pager<PostVO>> getMyPosts(Authentication authentication, @Valid @RequestParam(required = false, defaultValue = "0") Integer page, @Valid @RequestParam(required = false, defaultValue = "10") Integer size) {
        final String email = authentication.getName();
        final Long userId = userService.emailToId(email);
        Pager<PostVO> posts = postService.getPostsByUserId(userId, page, size);
        return CommonResponse.success(posts);
    }


    @PostMapping("/checkin")
    public CommonResponse<Void> checkinForAll(Authentication authentication) {
        final String email = authentication.getName();
        final Long userId = userService.emailToId(email);
        checkinService.checkinForAll(userId);
        return CommonResponse.success();
    }

    @GetMapping("/my_comments")
    public CommonResponse<Pager<CommentVO>> getMyComments(Authentication authentication, @Valid @RequestParam(required = false, defaultValue = "0") Integer page, @Valid @RequestParam(required = false, defaultValue = "10") Integer size) {
        final String email = authentication.getName();
        final Long userId = userService.emailToId(email);
        Pager<CommentVO> comments = commentService.getCommentsByUserId(userId, page, size);
        return CommonResponse.success(comments);
    }

}
