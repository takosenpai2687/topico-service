package usyd.elec5619.topicoservice.service;

import org.springframework.stereotype.Service;
import usyd.elec5619.topicoservice.dto.comment.CreateCommentDto;
import usyd.elec5619.topicoservice.type.SortBy;
import usyd.elec5619.topicoservice.vo.CommentVO;
import usyd.elec5619.topicoservice.vo.Pager;

@Service
public interface CommentService {
    Pager<CommentVO> getCommentsByUserId(Long userId, Integer page, Integer size);

    Pager<CommentVO> getCommentsByPostId(Long postId, Integer page, Integer size, SortBy sortBy);

    CommentVO createComment(Long userId, CreateCommentDto createCommentDto);

    void deleteComment(Long userId, Long commentId);

    void deleteAllCommentsByPostId(Long id);
}
