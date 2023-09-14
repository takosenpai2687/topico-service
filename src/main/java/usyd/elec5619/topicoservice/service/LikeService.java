package usyd.elec5619.topicoservice.service;

import org.springframework.stereotype.Service;

@Service
public interface LikeService {
    Boolean likePost(Long userId, Long postId);

    Boolean unlikePost(Long userId, Long postId);

    Boolean likeComment(Long userId, Long commentId);

    Boolean unlikeComment(Long userId, Long commentId);
}
