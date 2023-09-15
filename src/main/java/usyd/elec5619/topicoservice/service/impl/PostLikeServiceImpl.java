package usyd.elec5619.topicoservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import usyd.elec5619.topicoservice.exception.http.BadRequestException;
import usyd.elec5619.topicoservice.mapper.PostLikeMapper;
import usyd.elec5619.topicoservice.service.PostLikeService;
import usyd.elec5619.topicoservice.vo.LikeVO;

@Service
@RequiredArgsConstructor
public class PostLikeServiceImpl implements PostLikeService {

    private final PostLikeMapper postLikeMapper;

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
            postLikeMapper.unDislikePost(userId, postId);
        }
        postLikeMapper.likePost(userId, postId);
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
    public LikeVO unlikePost(Long userId, Long postId) {
        Boolean postLikedStatus = postLikeMapper.getPostLikedStatus(userId, postId);
        // Haven't liked before: do nothing
        if (postLikedStatus == null || !postLikedStatus) {
            throw new BadRequestException("Post already unliked");
        }
        // Liked before: unlike it
        postLikeMapper.unlikePost(userId, postId);
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
    public LikeVO dislikePost(Long userId, Long postId) {
        Boolean postLikedStatus = postLikeMapper.getPostLikedStatus(userId, postId);
        // Already disliked before: do nothing
        if (postLikedStatus != null && !postLikedStatus) {
            throw new BadRequestException("Post already disliked");
        }
        // Already liked before: remove like first
        if (postLikedStatus != null) {
            postLikeMapper.unlikePost(userId, postId);
        }
        postLikeMapper.dislikePost(userId, postId);
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
        postLikeMapper.unDislikePost(userId, postId);
        // Build result
        Boolean likedStatus = postLikeMapper.getPostLikedStatus(userId, postId);
        return LikeVO.builder()
                     .liked(likedStatus != null && likedStatus)
                     .disliked(likedStatus != null && !likedStatus)
                     .likes(postLikeMapper.getPostLikes(postId))
                     .dislikes(postLikeMapper.getPostDislikes(postId))
                     .build();
    }
}
