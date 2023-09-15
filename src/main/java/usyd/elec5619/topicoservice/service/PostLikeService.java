package usyd.elec5619.topicoservice.service;

import org.springframework.stereotype.Service;
import usyd.elec5619.topicoservice.vo.LikeVO;

@Service
public interface PostLikeService {
    LikeVO likePost(Long userId, Long postId);

    LikeVO unlikePost(Long userId, Long postId);

    LikeVO dislikePost(Long userId, Long postId);

    LikeVO unDislikePost(Long userId, Long postId);
}
