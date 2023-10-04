package usyd.elec5619.topicoservice.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import usyd.elec5619.topicoservice.dto.comment.CreateCommentDto;
import usyd.elec5619.topicoservice.exception.http.BadRequestException;
import usyd.elec5619.topicoservice.exception.http.InternalException;
import usyd.elec5619.topicoservice.exception.http.NotFoundException;
import usyd.elec5619.topicoservice.mapper.CommentLikeMapper;
import usyd.elec5619.topicoservice.mapper.CommentMapper;
import usyd.elec5619.topicoservice.mapper.PostMapper;
import usyd.elec5619.topicoservice.mapper.UserMapper;
import usyd.elec5619.topicoservice.model.Comment;
import usyd.elec5619.topicoservice.model.Post;
import usyd.elec5619.topicoservice.model.User;
import usyd.elec5619.topicoservice.service.CommentService;
import usyd.elec5619.topicoservice.service.NotificationService;
import usyd.elec5619.topicoservice.type.SortBy;
import usyd.elec5619.topicoservice.vo.CommentVO;
import usyd.elec5619.topicoservice.vo.Pager;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentMapper commentMapper;
    private final CommentLikeMapper commentLikeMapper;
    private final PostMapper postMapper;
    private final NotificationService notificationService;
    private final UserMapper userMapper;

    @Override
    //TODO: should be only comments or includes replies? any order rules?
    public Pager<CommentVO> getCommentsByUserId(Long userId, Integer page, Integer size) {
        final Integer offset = (page - 1) * size;
        List<Comment> commentList = commentMapper.getCommentsByUserId(userId, offset, size);
        Integer total = commentMapper.countCommentsByUserId(userId);
        return Pager.<CommentVO>builder().data(this.convertCommentToCommentVO(commentList)).page(page).size(size).total(total).build();
    }

    @Override
    public Pager<CommentVO> getCommentsByPostId(Long postId, Integer page, Integer size, SortBy sortBy) {
        final Integer offset = (page - 1) * size;
        List<CommentVO> commentVOList = sortBy.equals(SortBy.MOST_LIKES) ? commentMapper.getHotCommentsByPostId(postId, offset, size) : commentMapper.getNewCommentsByPostId(postId, offset, size);
        Integer total = commentMapper.countCommentsByPostId(postId);
        return Pager.<CommentVO>builder().data(commentVOList).page(page).size(size).total(total).build();
    }

    @Override
    //TODO: check replies for single comment?
    public Pager<CommentVO> getCommentsWithReplyByPostId(Long postId, Integer page, Integer size, SortBy sortBy) {
        final Integer offset = (page - 1) * size;
        return null;
    }


    @Override
    @Transactional
    public CommentVO createComment(Long userId, CreateCommentDto createCommentDto) {
        Long postId = createCommentDto.getPostId();
        Post post = postMapper.getPostById(postId)
                .orElseThrow(() -> new NotFoundException("Post with id " + postId + " does not exist"));
        //TODO: may be removed in the future as front_end changed
        if (createCommentDto.getParentId() != null &&
                commentMapper.getCommentIdsByPostId(postId).stream()
                        .noneMatch(id -> id.equals(createCommentDto.getParentId()))) {
            throw new BadRequestException("Parent comment does not belong to this post");
        }

        if (!Objects.equals(createCommentDto.getReplyToUserId(), postMapper.getAuthorIdByPostId(postId))) {
            throw new BadRequestException("Reply to user does not belong to this post");
        }

        //TODO: can be simplified
        Comment comment = Comment.builder()
                .postId(postId)
                .authorId(userId)
                .parentId(createCommentDto.getParentId())
                .replyToUserId(createCommentDto.getReplyToUserId())
                .content(createCommentDto.getContent())
                .build();
        commentMapper.insertOne(comment);
        Long commentId = comment.getId();
        // Is reply
        boolean isReply = comment.getParentId() != null;
        if (isReply) {
            notificationService.sendCommentReplyNotification(userId, comment.getReplyToUserId(), comment.getPostId(), commentId);
        } else {
            notificationService.sendCommentPostNotification(userId, comment.getPostId(), commentId);
        }
        Comment newComment = commentMapper.getCommentById(commentId).orElseThrow(() -> new InternalException("Failed to create comment"));


        User author = userMapper.getUserById(newComment.getAuthorId()).orElseThrow(() -> new NotFoundException("Author not found"));
        // add a reply to post it belongs to after the comment is inserted
        postMapper.addReplyToPost(newComment.getPostId());
        //add a reply to comment it belongs to after its reply is inserted
        commentMapper.addReplyToComment(newComment.getParentId());

        return CommentVO.builder()
                .id(commentId)
                .postId(newComment.getPostId())
                .postTitle(postMapper.getPostTitleById(newComment.getPostId()))
                .author(author)
              //  .children(convertCommentToCommentVO(commentMapper.getRepliesByCommentId(commentId)))
                .content(newComment.getContent())
                .likes(newComment.getLikes())
                .dislikes(newComment.getDislikes())
                .replies(newComment.getReplies())
                .utime(newComment.getUtime())
                .ctime(newComment.getUtime())
                .build();
    }

    //TODO: only check comments to show its children(reply)?
    public List<CommentVO> convertCommentToCommentVO(List<Comment> comments) {
        List<CommentVO> commentVOs = new ArrayList<>();
        for (Comment comment : comments) {
            User author = userMapper.getUserById(comment.getAuthorId()).orElseThrow(() -> new NotFoundException("Author not found"));
            CommentVO commentVO = CommentVO.builder()
                    .id(comment.getId())
                    .postId(comment.getPostId())
                    .postTitle(postMapper.getPostTitleById(comment.getPostId()))
                    .author(author)
                    .content(comment.getContent())
                    .likes(comment.getLikes())
                    .dislikes(comment.getDislikes())
                    .replies(comment.getReplies())
                    .ctime(comment.getCtime())
                    .utime(comment.getUtime())
                    .build();
            commentVOs.add(commentVO);
        }
        return commentVOs;
    }


//    @Override
//    @Transactional
//    public void deleteComment(Long userId, Long commentId) {
//        Comment comment = commentMapper.getCommentById(commentId).orElseThrow(() -> new NotFoundException("Comment not found"));
//        if (!comment.getAuthorId().equals(userId)) {
//            throw new BadRequestException("not your comment");
//        }
//        // Delete all likes of comment  and its replies in t_comment_like when delete a comment
//        // Delete all replies
//        List<Long> commentsAndRepliesIds = new ArrayList<>();
//        //if it is a comment
//        if (comment.getParentId() == null) {
//            commentMapper.getRepliesByCommentId(commentId).parallelStream().forEach(reply -> deleteComment(userId, reply.getId()));
//            commentsAndRepliesIds = commentMapper.getRepliesIdByCommentId(commentId);
//        }else{
//            //if it is just a reply
//            commentMapper.decrementReplyToComment(comment.getParentId());
//        }
//            commentsAndRepliesIds.add(commentId);
//        for (Long id : commentsAndRepliesIds) {
//            commentLikeMapper.deleteCommentLikesOrDislikes(id);
//        }
//
//        commentMapper.deleteOne(commentId);
//    }
@Override
@Transactional
public void deleteComment(Long userId, Long commentId) {
    Comment comment = commentMapper.getCommentById(commentId).orElseThrow(() -> new NotFoundException("Comment not found"));
    if (!comment.getAuthorId().equals(userId)) {
        throw new BadRequestException("not your comment");
    }

    // Delete all likes of comment and its replies in t_comment_like when delete a comment
    // If the comment has replies, delete all the replies
    if (comment.getParentId() == null) {
        List<Long> replyIds = commentMapper.getRepliesIdByCommentId(commentId);
        for (Long replyId : replyIds) {
            deleteComment(userId, replyId);
        }
    } else {
        // If it's just a reply, decrement the reply count of the parent comment
        commentMapper.decrementReplyToComment(comment.getParentId());
    }

    // Delete likes or dislikes related to this comment
    commentLikeMapper.deleteCommentLikesOrDislikes(commentId);

    // Delete the comment itself
    commentMapper.deleteOne(commentId);
}

    @Override
    public void deleteAllCommentsByPostId(Long id) {
        List<Comment> comments = commentMapper.getCommentsByPostId(id);
        comments.parallelStream().forEach(comment -> deleteComment(comment.getAuthorId(), comment.getId()));
    }
}
