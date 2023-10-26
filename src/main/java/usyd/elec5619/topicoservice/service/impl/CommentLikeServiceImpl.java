package usyd.elec5619.topicoservice.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import usyd.elec5619.topicoservice.exception.http.BadRequestException;
import usyd.elec5619.topicoservice.mapper.CommentLikeMapper;
import usyd.elec5619.topicoservice.service.CommentLikeService;
import usyd.elec5619.topicoservice.service.NotificationService;
import usyd.elec5619.topicoservice.vo.LikeVO;

@Service
@Slf4j
@RequiredArgsConstructor
public class CommentLikeServiceImpl implements CommentLikeService {

    private final CommentLikeMapper commentLikeMapper;
    private final NotificationService notificationService;

    public void mapperLikeComment(Long userId, Long commentId) {
        commentLikeMapper.insertLikeComment(userId, commentId);
        commentLikeMapper.incrementCommentLikes(commentId);
    }

    public void mapperUnlikeComment(Long userId, Long commentId) {
        commentLikeMapper.deleteLikeComment(userId, commentId);
        commentLikeMapper.decrementCommentLikes(commentId);
    }

    public void mapperDislikeComment(Long userId, Long commentId) {
        commentLikeMapper.insertDislikeComment(userId, commentId);
        commentLikeMapper.incrementCommentDislike(commentId);
    }

    public void mapperUnDislikeComment(Long userId, Long commentId) {
        commentLikeMapper.deleteDislikeComment(userId, commentId);
        commentLikeMapper.decrementCommentDislike(commentId);
    }

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
            this.mapperUnDislikeComment(userId, commentId);
        }
        this.mapperLikeComment(userId, commentId);
        // Send notification
        notificationService.sendLikeCommentNotification(userId, commentId, true);
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
        this.mapperUnlikeComment(userId, commentId);
        // Send unlike notification
        notificationService.sendLikeCommentNotification(userId, commentId, false);
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
            this.mapperUnlikeComment(userId, commentId);
            // Send unlike notification
            notificationService.sendLikeCommentNotification(userId, commentId, false);
        }
        this.mapperDislikeComment(userId, commentId);
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
        this.mapperUnDislikeComment(userId, commentId);
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
    public LikeVO getCommentLikeStatus(Long userId, Long commentId) {
        final Boolean likedStatus = commentLikeMapper.getCommentLikedStatus(userId, commentId);
        final Boolean liked = likedStatus != null && likedStatus;
        final Boolean disliked = likedStatus != null && !likedStatus;
        return LikeVO.builder()
                     .liked(liked)
                     .disliked(disliked)
                     .likes(commentLikeMapper.getCommentLikes(commentId))
                     .dislikes(commentLikeMapper.getCommentDislikes(commentId))
                     .build();
    }
}
