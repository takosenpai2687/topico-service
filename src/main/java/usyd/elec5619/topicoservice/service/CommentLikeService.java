package usyd.elec5619.topicoservice.service;

import org.springframework.stereotype.Service;
import usyd.elec5619.topicoservice.vo.LikeVO;

@Service
public interface CommentLikeService {

    LikeVO likeComment(Long userId, Long commentId);

    LikeVO unlikeComment(Long userId, Long commentId);

    LikeVO dislikeComment(Long userId, Long commentId);

    LikeVO unDislikeComment(Long userId, Long commentId);

    LikeVO getCommentLikeStatus(Long userId, Long commentId);
}
