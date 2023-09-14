package usyd.elec5619.topicoservice.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import usyd.elec5619.topicoservice.dto.comment.CreateCommentDto;
import usyd.elec5619.topicoservice.mapper.CommentMapper;
import usyd.elec5619.topicoservice.model.Comment;
import usyd.elec5619.topicoservice.service.CommentService;
import usyd.elec5619.topicoservice.type.SortBy;
import usyd.elec5619.topicoservice.vo.CommentVO;
import usyd.elec5619.topicoservice.vo.Pager;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentMapper commentMapper;

    @Override
    public Pager<CommentVO> getCommentsByUserId(Long userId, Integer page, Integer size) {
        final Integer offset = page * size;
        List<CommentVO> commentVOList = commentMapper.getCommentsByUserId(userId, offset, size);
        Integer total = commentMapper.countCommentsByUserId(userId);
        return Pager.<CommentVO>builder()
                    .data(commentVOList)
                    .page(page)
                    .size(size)
                    .total(total)
                    .build();
    }

    @Override
    public Pager<CommentVO> getCommentsByPostId(Long postId, Integer page, Integer size, SortBy sortBy) {
        final Integer offset = page * size;
        List<CommentVO> commentVOList = sortBy.equals(SortBy.MOST_LIKES)
                ? commentMapper.getHotCommentsByPostId(postId, offset, size)
                : commentMapper.getNewCommentsByPostId(postId, offset, size);
        Integer total = commentMapper.countCommentsByPostId(postId);
        return Pager.<CommentVO>builder()
                    .data(commentVOList)
                    .page(page)
                    .size(size)
                    .total(total)
                    .build();
    }

    @Override
    public void createComment(Long userId, CreateCommentDto createCommentDto) {
        Comment comment = Comment.builder()
                                 .postId(createCommentDto.getPostId())
                                 .authorId(userId)
                                 .parentId(createCommentDto.getParentId())
                                 .replyToUserId(createCommentDto.getReplyToUserId())
                                 .content(createCommentDto.getContent())
                                 .build();
        commentMapper.insertOne(comment);
    }
}
