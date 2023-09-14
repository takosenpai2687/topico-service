package usyd.elec5619.topicoservice.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import usyd.elec5619.topicoservice.dto.comment.CreateCommentDto;
import usyd.elec5619.topicoservice.pojo.CommonResponse;
import usyd.elec5619.topicoservice.service.CommentService;
import usyd.elec5619.topicoservice.service.UserService;
import usyd.elec5619.topicoservice.vo.CommentVO;

@RestController("/api/v1/comments")
@RequiredArgsConstructor
public class CommentController {

    private final UserService userService;

    private final CommentService commentService;

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

}
