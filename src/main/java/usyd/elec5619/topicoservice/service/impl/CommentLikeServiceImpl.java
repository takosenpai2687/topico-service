package usyd.elec5619.topicoservice.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import usyd.elec5619.topicoservice.exception.http.BadRequestException;
import usyd.elec5619.topicoservice.mapper.CommentLikeMapper;
import usyd.elec5619.topicoservice.service.CommentLikeService;
import usyd.elec5619.topicoservice.vo.LikeVO;

@Service
@Slf4j
@RequiredArgsConstructor
public class CommentLikeServiceImpl implements CommentLikeService {

    private final CommentLikeMapper commentLikeMapper;


    @Override
    @Transactional
    public LikeVO likeComment(Long userId, Long commentId) {
        Boolean commentLikedStatus = commentLikeMapper.getCommentLikedStatus(userId, commentId);
        // Already liked before: do nothing
        if (commentLikedStatus != null && commentLikedStatus) {
            throw new BadRequestException("Comment already liked");
        }
        // Already disliked before: remove dislike first
        if (commentLikedStatus != null) {
            commentLikeMapper.unDislikeComment(userId, commentId);
        }
        commentLikeMapper.likeComment(userId, commentId);
        // Build result
        Boolean likedStatus = commentLikeMapper.getCommentLikedStatus(userId, commentId);
        return LikeVO.builder()
                     .liked(likedStatus != null && likedStatus)
                     .disliked(likedStatus != null && !likedStatus)
                     .likes(commentLikeMapper.getCommentLikes(commentId))
                     .dislikes(commentLikeMapper.getCommentDislikes(commentId))
                     .build();
    }

    @Override
    @Transactional
    public LikeVO unlikeComment(Long userId, Long commentId) {
        Boolean commentLikedStatus = commentLikeMapper.getCommentLikedStatus(userId, commentId);
        // Haven't liked before: do nothing
        if (commentLikedStatus == null || !commentLikedStatus) {
            throw new BadRequestException("Comment already unliked");
        }
        // Liked before: unlike it
        commentLikeMapper.unlikeComment(userId, commentId);
        // Build result
        Boolean likedStatus = commentLikeMapper.getCommentLikedStatus(userId, commentId);
        return LikeVO.builder()
                     .liked(likedStatus != null && likedStatus)
                     .disliked(likedStatus != null && !likedStatus)
                     .likes(commentLikeMapper.getCommentLikes(commentId))
                     .dislikes(commentLikeMapper.getCommentDislikes(commentId))
                     .build();
    }

    @Override
    @Transactional
    public LikeVO dislikeComment(Long userId, Long commentId) {
        Boolean commentLikedStatus = commentLikeMapper.getCommentLikedStatus(userId, commentId);
        // Already disliked before: do nothing
        if (commentLikedStatus != null && !commentLikedStatus) {
            throw new BadRequestException("Comment already disliked");
        }
        // Already liked before: remove like first
        if (commentLikedStatus != null) {
            commentLikeMapper.unlikeComment(userId, commentId);
        }
        commentLikeMapper.dislikeComment(userId, commentId);
        // Build result
        Boolean likedStatus = commentLikeMapper.getCommentLikedStatus(userId, commentId);
        return LikeVO.builder()
                     .liked(likedStatus != null && likedStatus)
                     .disliked(likedStatus != null && !likedStatus)
                     .likes(commentLikeMapper.getCommentLikes(commentId))
                     .dislikes(commentLikeMapper.getCommentDislikes(commentId))
                     .build();
    }

    @Override
    @Transactional
    public LikeVO unDislikeComment(Long userId, Long commentId) {
        Boolean commentLikedStatus = commentLikeMapper.getCommentLikedStatus(userId, commentId);
        // Haven't disliked before: do nothing
        if (commentLikedStatus == null || commentLikedStatus) {
            throw new BadRequestException("Comment already un-disliked");
        }
        // Disliked before: unDislike it
        commentLikeMapper.unDislikeComment(userId, commentId);
        // Build result
        Boolean likedStatus = commentLikeMapper.getCommentLikedStatus(userId, commentId);
        return LikeVO.builder()
                     .liked(likedStatus != null && likedStatus)
                     .disliked(likedStatus != null && !likedStatus)
                     .likes(commentLikeMapper.getCommentLikes(commentId))
                     .dislikes(commentLikeMapper.getCommentDislikes(commentId))
                     .build();
    }
}
