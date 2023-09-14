package usyd.elec5619.topicoservice.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import usyd.elec5619.topicoservice.dto.comment.CreateCommentDto;
import usyd.elec5619.topicoservice.model.User;
import usyd.elec5619.topicoservice.pojo.CommonResponse;
import usyd.elec5619.topicoservice.service.CommentService;
import usyd.elec5619.topicoservice.service.UserService;

@RestController("/api/v1/comments")
@RequiredArgsConstructor
public class CommentController {

    private final UserService userService;

    private final CommentService commentService;

    @PostMapping()
    public CommonResponse<Void> createComment(Authentication authentication, @Valid @RequestBody CreateCommentDto createCommentDto) {
        final String email = authentication.getName();
        final Long userId = userService.emailToId(email);
        commentService.createComment(userId, createCommentDto);
        return CommonResponse.success();
    }
 
}
