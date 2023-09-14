package usyd.elec5619.topicoservice.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import usyd.elec5619.topicoservice.exception.http.BadRequestException;
import usyd.elec5619.topicoservice.mapper.LikeMapper;
import usyd.elec5619.topicoservice.service.LikeService;

import java.util.Objects;

@Service
@Slf4j
@RequiredArgsConstructor
public class LikeServiceImpl implements LikeService {

    private final LikeMapper likeMapper;

    public Boolean getPostLikedStatus(Long userId, Long postId) {
        return Objects.requireNonNullElse(likeMapper.getPostLikedStatus(userId, postId), false);
    }

    public Boolean getCommentLikedStatus(Long userId, Long commentId) {
        return Objects.requireNonNullElse(likeMapper.getCommentLikedStatus(userId, commentId), false);
    }

    @Override
    public Boolean likePost(Long userId, Long postId) {
        if (getPostLikedStatus(userId, postId)) throw new BadRequestException("You have already liked this post");
        likeMapper.likePost(userId, postId);
        return getPostLikedStatus(userId, postId);
    }

    @Override
    public Boolean unlikePost(Long userId, Long postId) {
        if (!getPostLikedStatus(userId, postId)) throw new BadRequestException("You have already unliked this post");
        likeMapper.unlikePost(userId, postId);
        return getPostLikedStatus(userId, postId);
    }

    @Override
    public Boolean likeComment(Long userId, Long commentId) {
        if (getCommentLikedStatus(userId, commentId))
            throw new BadRequestException("You have already liked this comment");
        likeMapper.likeComment(userId, commentId);
        return getCommentLikedStatus(userId, commentId);
    }

    @Override
    public Boolean unlikeComment(Long userId, Long commentId) {
        if (!getCommentLikedStatus(userId, commentId))
            throw new BadRequestException("You have already unliked this comment");
        likeMapper.unlikeComment(userId, commentId);
        return getCommentLikedStatus(userId, commentId);
    }
}
