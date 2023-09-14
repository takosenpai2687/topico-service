package usyd.elec5619.topicoservice.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import usyd.elec5619.topicoservice.dto.post.CreatePostDto;
import usyd.elec5619.topicoservice.pojo.CommonResponse;
import usyd.elec5619.topicoservice.service.PostService;
import usyd.elec5619.topicoservice.service.UserService;
import usyd.elec5619.topicoservice.vo.PostVO;

@RestController("/api/v1/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final UserService userService;

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
}
