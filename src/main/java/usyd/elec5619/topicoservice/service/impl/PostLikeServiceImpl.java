package usyd.elec5619.topicoservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import usyd.elec5619.topicoservice.exception.http.BadRequestException;
import usyd.elec5619.topicoservice.mapper.PostLikeMapper;
import usyd.elec5619.topicoservice.service.NotificationService;
import usyd.elec5619.topicoservice.service.PostLikeService;
import usyd.elec5619.topicoservice.vo.LikeVO;

@Service
@RequiredArgsConstructor
public class PostLikeServiceImpl implements PostLikeService {

    private final PostLikeMapper postLikeMapper;
    private final NotificationService notificationService;

    public void mapperLikePost(Long userId, Long postId) {
        postLikeMapper.insertUserLikePost(userId, postId);
        postLikeMapper.updatePostLikes(postId);
    }

    public void mapperUnlikePost(Long userId, Long postId) {
        postLikeMapper.deleteUserLikePost(userId, postId);
        postLikeMapper.decrementPostLikes(postId);
    }

    public void mapperDislikePost(Long userId, Long postId) {
        postLikeMapper.insertUserDislikePost(userId, postId);
        postLikeMapper.incrementPostDislikes(postId);
    }

    public void mapperUnDislikePost(Long userId, Long postId) {
        postLikeMapper.deleteUserDislikePost(userId, postId);
        postLikeMapper.decrementPostDislikes(postId);
    }

    @Override
    @Transactional
    public LikeVO likePost(Long userId, Long postId) {
        Boolean postLikedStatus = postLikeMapper.getPostLikedStatus(userId, postId);
        // Already liked before: do nothing
        if (postLikedStatus != null && postLikedStatus) {
            throw new BadRequestException("Post already liked");
        }
        // Already disliked before: remove dislike first
        if (postLikedStatus != null) {
            mapperUnDislikePost(userId, postId);
        }
        mapperLikePost(userId, postId);

        // Build result
        Boolean likedStatus = postLikeMapper.getPostLikedStatus(userId, postId);
        // Send notification
        notificationService.sendLikePostNotification(userId, postId, true);
        return LikeVO.builder()
                     .liked(likedStatus != null && likedStatus)
                     .disliked(likedStatus != null && !likedStatus)
                     .likes(postLikeMapper.getPostLikes(postId))
                     .dislikes(postLikeMapper.getPostDislikes(postId))
                     .build();
    }

    @Override
    @Transactional
    public LikeVO unlikePost(Long userId, Long postId) {
        Boolean postLikedStatus = postLikeMapper.getPostLikedStatus(userId, postId);
        // Haven't liked before: do nothing
        if (postLikedStatus == null || !postLikedStatus) {
            throw new BadRequestException("Post already unliked");
        }
        // Liked before: unlike it
        mapperUnlikePost(userId, postId);
        // Build result
        Boolean likedStatus = postLikeMapper.getPostLikedStatus(userId, postId);
        // Send notification
        notificationService.sendLikePostNotification(userId, postId, false);
        return LikeVO.builder()
                     .liked(likedStatus != null && likedStatus)
                     .disliked(likedStatus != null && !likedStatus)
                     .likes(postLikeMapper.getPostLikes(postId))
                     .dislikes(postLikeMapper.getPostDislikes(postId))
                     .build();
    }

    @Override
    @Transactional
    public LikeVO dislikePost(Long userId, Long postId) {
        Boolean postLikedStatus = postLikeMapper.getPostLikedStatus(userId, postId);
        // Already disliked before: do nothing
        if (postLikedStatus != null && !postLikedStatus) {
            throw new BadRequestException("Post already disliked");
        }
        // Already liked before: remove like first
        if (postLikedStatus != null) {
            mapperUnlikePost(userId, postId);
            // Send unlike notification
            notificationService.sendLikePostNotification(userId, postId, false);
        }
        mapperDislikePost(userId, postId);
        // Build result
        Boolean likedStatus = postLikeMapper.getPostLikedStatus(userId, postId);
        return LikeVO.builder()
                     .liked(likedStatus != null && likedStatus)
                     .disliked(likedStatus != null && !likedStatus)
                     .likes(postLikeMapper.getPostLikes(postId))
                     .dislikes(postLikeMapper.getPostDislikes(postId))
                     .build();
    }

    @Override
    @Transactional
    public LikeVO unDislikePost(Long userId, Long postId) {
        Boolean postLikedStatus = postLikeMapper.getPostLikedStatus(userId, postId);
        // Haven't disliked before: do nothing
        if (postLikedStatus == null || postLikedStatus) {
            throw new BadRequestException("Post already un-disliked");
        }
        // Disliked before: unDislike it
        mapperUnDislikePost(userId, postId);
        // Build result
        Boolean likedStatus = postLikeMapper.getPostLikedStatus(userId, postId);
        return LikeVO.builder()
                     .liked(likedStatus != null && likedStatus)
                     .disliked(likedStatus != null && !likedStatus)
                     .likes(postLikeMapper.getPostLikes(postId))
                     .dislikes(postLikeMapper.getPostDislikes(postId))
                     .build();
    }

    @Override
    public LikeVO getPostLikeStatus(Long userId, Long postId) {
        final Boolean likedStatus = postLikeMapper.getPostLikedStatus(userId, postId);
        final Boolean liked = likedStatus != null && likedStatus;
        final Boolean disliked = likedStatus != null && !likedStatus;
        return LikeVO.builder()
                     .liked(liked)
                     .disliked(disliked)
                     .likes(postLikeMapper.getPostLikes(postId))
                     .dislikes(postLikeMapper.getPostDislikes(postId))
                     .build();
    }


}
