package usyd.elec5619.topicoservice.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import usyd.elec5619.topicoservice.dto.post.CreatePostDto;
import usyd.elec5619.topicoservice.pojo.CommonResponse;
import usyd.elec5619.topicoservice.service.CommentService;
import usyd.elec5619.topicoservice.service.PostLikeService;
import usyd.elec5619.topicoservice.service.PostService;
import usyd.elec5619.topicoservice.service.UserService;
import usyd.elec5619.topicoservice.type.SortBy;
import usyd.elec5619.topicoservice.vo.CommentVO;
import usyd.elec5619.topicoservice.vo.LikeVO;
import usyd.elec5619.topicoservice.vo.Pager;
import usyd.elec5619.topicoservice.vo.PostVO;

@RestController()
@RequestMapping("/api/v1/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final UserService userService;
    private final PostLikeService postLikeService;
    private final CommentService commentService;

    @GetMapping("/{id}")
    public CommonResponse<PostVO> getPostVOById(@Valid @PathVariable("id") Long postId) {
        PostVO post = postService.getPostVOById(postId);
        return CommonResponse.success(post);
    }

    @PostMapping()
    public CommonResponse<PostVO> createPost(Authentication authentication, @Valid @RequestBody CreatePostDto createPostDto, HttpServletRequest request) {
        final Long userId = Long.parseLong(authentication.getName());
        final String clientIp = request.getRemoteAddr();
        PostVO post = postService.createPost(userId, createPostDto, clientIp);
        return CommonResponse.success(post);
    }

    @DeleteMapping("/{id}")
    public CommonResponse<Void> deletePost(Authentication authentication, @Valid @PathVariable("id") Long postId) {
        final Long userId = Long.parseLong(authentication.getName());
        postService.deletePost(userId, postId);
        return CommonResponse.success();
    }

    // Likes
    @PostMapping("/like/{id}")
    public CommonResponse<LikeVO> likePost(Authentication authentication, @Valid @PathVariable("id") Long postId) {
        final Long userId = Long.parseLong(authentication.getName());
        LikeVO likeVO = postLikeService.likePost(userId, postId);
        return CommonResponse.success(likeVO);
    }

    @DeleteMapping("/like/{id}")
    public CommonResponse<LikeVO> unlikePost(Authentication authentication, @Valid @PathVariable("id") Long postId) {
        final Long userId = Long.parseLong(authentication.getName());
        LikeVO likeVO = postLikeService.unlikePost(userId, postId);
        return CommonResponse.success(likeVO);
    }

    @PostMapping("/dislike/{id}")
    public CommonResponse<LikeVO> dislikePost(Authentication authentication, @Valid @PathVariable("id") Long postId) {
        final Long userId = Long.parseLong(authentication.getName());
        LikeVO likeVO = postLikeService.dislikePost(userId, postId);
        return CommonResponse.success(likeVO);
    }

    @DeleteMapping("/dislike/{id}")
    public CommonResponse<LikeVO> unDislikePost(Authentication authentication, @Valid @PathVariable("id") Long postId) {
        final Long userId = Long.parseLong(authentication.getName());
        LikeVO likeVO = postLikeService.unDislikePost(userId, postId);
        return CommonResponse.success(likeVO);
    }

    @GetMapping("/post_comments/{postId}")
    public CommonResponse<Pager<CommentVO>> getPostComments(@PathVariable Long postId, @RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer size, @RequestParam(defaultValue = "MOST_LIKES") SortBy sortBy) {
        final Pager<CommentVO> comments = commentService.getCommentsByPostId(postId, page, size, sortBy);
        return CommonResponse.success(comments);
    }

    @GetMapping("/like_status/{postId}")
    public CommonResponse<LikeVO> getPostLikeStatus(Authentication authentication, @PathVariable Long postId) {
        final Long userId = Long.parseLong(authentication.getName());
        final LikeVO likeVO = postLikeService.getPostLikeStatus(userId, postId);
        return CommonResponse.success(likeVO);
    }
}
