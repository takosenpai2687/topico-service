package usyd.elec5619.topicoservice.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import usyd.elec5619.topicoservice.dto.comment.CreateCommentDto;
import usyd.elec5619.topicoservice.pojo.CommonResponse;
import usyd.elec5619.topicoservice.service.CommentService;
import usyd.elec5619.topicoservice.service.CommentLikeService;
import usyd.elec5619.topicoservice.service.UserService;
import usyd.elec5619.topicoservice.vo.CommentVO;
import usyd.elec5619.topicoservice.vo.LikeVO;
import usyd.elec5619.topicoservice.vo.Pager;

@RestController()
@RequestMapping("/api/v1/comments")
@RequiredArgsConstructor
public class CommentController {

    private final UserService userService;

    private final CommentService commentService;

    private final CommentLikeService commentLikeService;

    @PostMapping("/")
    public CommonResponse<CommentVO> createComment(Authentication authentication, @Valid @RequestBody CreateCommentDto createCommentDto) {
        final String email = authentication.getName();
        final Long userId = userService.emailToId(email);
        CommentVO commentVO = commentService.createComment(userId, createCommentDto);
        return CommonResponse.success(commentVO);
    }

    @DeleteMapping("/{id}")
    public CommonResponse<Void> deleteComment(Authentication authentication, @Valid @PathVariable("id") Long commentId) {
        final String email = authentication.getName();
        final Long userId = userService.emailToId(email);
        commentService.deleteComment(userId, commentId);
        return CommonResponse.success();
    }

    @GetMapping("/{id}")
    public CommonResponse<Pager<CommentVO>> getCommentReplies(@Valid @PathVariable("id") Long commentId,  @RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer size) {
        final Pager<CommentVO> commentWithRepliesVO = commentService.getSingleCommentRepliesByPostId(commentId, page, size);
        return CommonResponse.success(commentWithRepliesVO);
    }

    /**
     * Like a comment
     */
    @PostMapping("/like/{id}")
    public CommonResponse<LikeVO> likeComment(Authentication authentication, @Valid @PathVariable("id") Long commentId) {
        final String email = authentication.getName();
        final Long userId = userService.emailToId(email);
        LikeVO likeVO = commentLikeService.likeComment(userId, commentId);
        return CommonResponse.success(likeVO);
    }

    /**
     * Unlike a comment
     */
    @DeleteMapping("/like/{id}")
    public CommonResponse<LikeVO> unlikeComment(Authentication authentication, @Valid @PathVariable("id") Long commentId) {
        final String email = authentication.getName();
        final Long userId = userService.emailToId(email);
        LikeVO likeVO = commentLikeService.unlikeComment(userId, commentId);
        return CommonResponse.success(likeVO);
    }

    /**
     * Dislike a comment
     */
    @PostMapping("/dislike/{id}")
    public CommonResponse<LikeVO> dislikeComment(Authentication authentication, @Valid @PathVariable("id") Long commentId) {
        final String email = authentication.getName();
        final Long userId = userService.emailToId(email);
        LikeVO likeVO = commentLikeService.dislikeComment(userId, commentId);
        return CommonResponse.success(likeVO);
    }

    /**
     * UnDislike a comment
     */
    @DeleteMapping("/dislike/{id}")
    public CommonResponse<LikeVO> unDislikeComment(Authentication authentication, @Valid @PathVariable("id") Long commentId) {
        final String email = authentication.getName();
        final Long userId = userService.emailToId(email);
        LikeVO likeVO = commentLikeService.unDislikeComment(userId, commentId);
        return CommonResponse.success(likeVO);
    }
}
