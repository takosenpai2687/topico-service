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
import usyd.elec5619.topicoservice.service.LocationService;
import usyd.elec5619.topicoservice.service.NotificationService;
import usyd.elec5619.topicoservice.type.SortBy;
import usyd.elec5619.topicoservice.vo.CommentVO;
import usyd.elec5619.topicoservice.vo.Pager;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentMapper commentMapper;
    private final CommentLikeMapper commentLikeMapper;
    private final PostMapper postMapper;
    private final NotificationService notificationService;
    private final UserMapper userMapper;
    private final LocationService locationService;

    @Override
    //TODO:  only comments, do not includes replies,  any order rules on likes?
    /*
    在评论可以翻页，每页显示10条的情况下，回复不能同时翻页，只能最多显示前10条回复(可设置)，如果回复超过10条，需要点击评论/显示“查看更多回复”按钮，点击后显示所有回复。
     */
    public Pager<CommentVO> getCommentsByUserId(Long userId, Integer page, Integer size) {
        final Integer offset = (page - 1) * size;
        List<Comment> commentList = commentMapper.getCommentsByUserId(userId, offset, size);
        Integer total = commentMapper.countCommentsByUserId(userId);
        return Pager.<CommentVO>builder().data(this.convertCommentToCommentVO(commentList, offset, size)).page(page).size(size).total(total).build();
    }

    @Override
    //TODO: only comments, do not includes replies
    public Pager<CommentVO> getCommentsByPostId(Long postId, Integer page, Integer size, SortBy sortBy) {
        final Integer offset = (page - 1) * size;
        Optional<Post> optionalPost = postMapper.getPostById(postId);
        if (!optionalPost.isPresent()) {
            throw new NotFoundException("Post not found");
        }
        List<Comment> commentList = commentMapper.getHotAndNewCommentsByPostId(postId, offset, size);
        Integer total = commentMapper.countCommentsByPostId(postId);
        return Pager.<CommentVO>builder().data(this.convertCommentToCommentVO(commentList, offset, size)).page(page).size(size).total(total).build();
    }

    @Override
    //TODO: check replies for single comment, when click the comment, show its replies
    public Pager<CommentVO> getSingleCommentRepliesByPostId(Long commentId, Integer page, Integer size) {
        final Integer offset = (page - 1) * size;
        Optional<Comment> optionalComment = commentMapper.getCommentById(commentId);
        if (!optionalComment.isPresent()) {
            throw new NotFoundException("Comment not found");
        }
        if (optionalComment.get().getParentId() != null) {
            throw new BadRequestException("Not a comment");
        }
        List<Comment> replyList = commentMapper.getRepliesByCommentId(commentId, offset, size);
        if (replyList == null || replyList.isEmpty()) {
            throw new NotFoundException("This comment has no reply");
        }

        Integer total = commentMapper.countRepliesByCommentId(commentId);
        List<CommentVO> replyVOList = this.convertCommentToCommentVO(replyList, offset, size);
        return Pager.<CommentVO>builder()
                    .data(replyVOList)
                    .page(page)
                    .size(size)
                    .total(total)
                    .build();
    }

    @Override
    @Transactional
    public CommentVO createComment(Long userId, CreateCommentDto createCommentDto, String clientIp) {
        Long postId = createCommentDto.getPostId();
        Post post = postMapper.getPostById(postId)
                              .orElseThrow(() -> new NotFoundException("Post with id " + postId + " does not exist"));
        //TODO: may be removed in the future as front_end changed
        if (createCommentDto.getParentId() != null &&
                commentMapper.getCommentIdsByPostId(postId).stream()
                             .noneMatch(id -> id.equals(createCommentDto.getParentId()))) {
            throw new BadRequestException("Parent comment  does not exist or does not belong to this post");
        }

        final Long replyToUserId = createCommentDto.getReplyToUserId();
        final Long postAuthorId = postMapper.getAuthorIdByPostId(postId);
        if (replyToUserId != null && !Objects.equals(replyToUserId, postAuthorId)) {
            throw new BadRequestException("Reply to user does not belong to this post");
        }

        final String city = locationService.getCity(clientIp);

        //TODO: can be simplified
        Comment comment = Comment.builder()
                                 .postId(postId)
                                 .authorId(userId)
                                 .parentId(createCommentDto.getParentId())
                                 .replyToUserId(replyToUserId)
                                 .content(createCommentDto.getContent())
                                 .location(city)
                                 .imageId(createCommentDto.getImageId())
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
                        .content(newComment.getContent())
                        .likes(newComment.getLikes())
                        .dislikes(newComment.getDislikes())
                        .replies(newComment.getReplies())
                        .utime(newComment.getUtime())
                        .ctime(newComment.getUtime())
                        .location(newComment.getLocation())
                        .image(newComment.getImageId())
                        .build();

    }


    //TODO: only check comments to show its children(reply)?
    public List<CommentVO> convertCommentToCommentVO(List<Comment> comments, Integer offset, Integer size) {
        List<CommentVO> commentVOs = new ArrayList<>();
        for (Comment comment : comments) {
            User author = userMapper.getUserById(comment.getAuthorId()).orElseThrow(() -> new NotFoundException("Author not found"));
            CommentVO commentVO = CommentVO.builder()
                                           .id(comment.getId())
                                           .postId(comment.getPostId())
                                           .postTitle(postMapper.getPostTitleById(comment.getPostId()))
                                           .author(author)
                                           .children(convertCommentToCommentVO(commentMapper.getRepliesByCommentId(comment.getId(), 0, 10), offset, size))
                                           .content(comment.getContent())
                                           .likes(comment.getLikes())
                                           .dislikes(comment.getDislikes())
                                           .replies(comment.getReplies())
                                           .ctime(comment.getCtime())
                                           .utime(comment.getUtime())
                                           .location(comment.getLocation())
                                           .image(comment.getImageId())
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

        commentLikeMapper.deleteCommentLikesOrDislikes(commentId);

        commentMapper.deleteOne(commentId);
    }

    @Override
    public void deleteAllCommentsByPostId(Long id) {
        List<Comment> comments = commentMapper.getCommentsByPostId(id);
        comments.parallelStream().forEach(comment -> deleteComment(comment.getAuthorId(), comment.getId()));
    }
}
