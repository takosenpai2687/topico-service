package usyd.elec5619.topicoservice.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
@RequestMapping("/api/v1/home")
public class HomeController {

    private final UserService userService;
    private final PostService postService;
    private final CommunityService communityService;
    private final CheckinService checkinService;
    private final CommentService commentService;

    @GetMapping("/communities_following")
    public CommonResponse<List<Community>> getCommunitiesFollowing(Authentication authentication) {
        final Long userId = Long.parseLong(authentication.getName());
        List<Community> communitiesFollowing = communityService.getCommunitiesFollowedByUser(userId);
        return CommonResponse.success(communitiesFollowing);
    }

    @GetMapping("/communities_recommended")
    public CommonResponse<List<Community>> getCommunitiesRecommended(Authentication authentication, @RequestParam(required = false, defaultValue = "24") Integer limit) {
        final Long userId = Long.parseLong(authentication.getName());
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
        final Long userId = Long.parseLong(authentication.getName());
        Pager<PostVO> posts = postService.getPostsByUserId(userId, page, size);
        return CommonResponse.success(posts);
    }


    @PostMapping("/checkin")
    public CommonResponse<Void> checkinForAll(Authentication authentication) {
        final Long userId = Long.parseLong(authentication.getName());
        checkinService.checkinForAll(userId);
        return CommonResponse.success();
    }

    @GetMapping("/my_comments")
    public CommonResponse<Pager<CommentVO>> getMyComments(Authentication authentication, @Valid @RequestParam(required = false, defaultValue = "1") Integer page, @Valid @RequestParam(required = false, defaultValue = "10") Integer size) {
        final Long userId = Long.parseLong(authentication.getName());
        Pager<CommentVO> comments = commentService.getCommentsByUserId(userId, page, size);
        return CommonResponse.success(comments);
    }


}
