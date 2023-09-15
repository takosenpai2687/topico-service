package usyd.elec5619.topicoservice.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import usyd.elec5619.topicoservice.dto.post.CreatePostDto;
import usyd.elec5619.topicoservice.pojo.CommonResponse;
import usyd.elec5619.topicoservice.service.PostLikeService;
import usyd.elec5619.topicoservice.service.PostService;
import usyd.elec5619.topicoservice.service.UserService;
import usyd.elec5619.topicoservice.vo.LikeVO;
import usyd.elec5619.topicoservice.vo.PostVO;

@RestController()
@RequestMapping("/api/v1/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final UserService userService;
    private final PostLikeService postLikeService;

    @GetMapping("/{id}")
    public CommonResponse<PostVO> getPostById(@Valid @PathVariable("id") Long id) {
        PostVO post = postService.getPostById(id);
        return CommonResponse.success(post);
    }

    @PostMapping("/")
    public CommonResponse<PostVO> createPost(Authentication authentication, @Valid @RequestBody CreatePostDto createPostDto) {
        final String email = authentication.getName();
        final Long userId = userService.emailToId(email);
        PostVO post = postService.createPost(userId, createPostDto);
        return CommonResponse.success(post);
    }

    @DeleteMapping("/{id}")
    public CommonResponse<Void> deletePost(Authentication authentication, @Valid @PathVariable("id") Long postId) {
        final String email = authentication.getName();
        final Long userId = userService.emailToId(email);
        postService.deletePost(userId, postId);
        return CommonResponse.success();
    }

    // Likes
    @PostMapping("/like/{id}")
    public CommonResponse<LikeVO> likePost(Authentication authentication, @Valid @PathVariable("id") Long postId) {
        final String email = authentication.getName();
        final Long userId = userService.emailToId(email);
        LikeVO likeVO = postLikeService.likePost(userId, postId);
        return CommonResponse.success(likeVO);
    }

    @DeleteMapping("/like/{id}")
    public CommonResponse<LikeVO> unlikePost(Authentication authentication, @Valid @PathVariable("id") Long postId) {
        final String email = authentication.getName();
        final Long userId = userService.emailToId(email);
        LikeVO likeVO = postLikeService.unlikePost(userId, postId);
        return CommonResponse.success(likeVO);
    }

    @PostMapping("/dislike/{id}")
    public CommonResponse<LikeVO> dislikePost(Authentication authentication, @Valid @PathVariable("id") Long postId) {
        final String email = authentication.getName();
        final Long userId = userService.emailToId(email);
        LikeVO likeVO = postLikeService.dislikePost(userId, postId);
        return CommonResponse.success(likeVO);
    }

    @DeleteMapping("/dislike/{id}")
    public CommonResponse<LikeVO> unDislikePost(Authentication authentication, @Valid @PathVariable("id") Long postId) {
        final String email = authentication.getName();
        final Long userId = userService.emailToId(email);
        LikeVO likeVO = postLikeService.unDislikePost(userId, postId);
        return CommonResponse.success(likeVO);
    }
}
