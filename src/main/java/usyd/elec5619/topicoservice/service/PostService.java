package usyd.elec5619.topicoservice.service;

import org.springframework.stereotype.Service;
import usyd.elec5619.topicoservice.dto.post.CreatePostDto;
import usyd.elec5619.topicoservice.type.SortBy;
import usyd.elec5619.topicoservice.vo.Pager;
import usyd.elec5619.topicoservice.vo.PostVO;

import java.util.List;

@Service
public interface PostService {
    Pager<PostVO> getPostsByUserId(Long userId, Integer page, Integer size);

    Pager<PostVO> getPostsByCommunityId(Long communityId, Integer page, Integer size, SortBy sortBy);

    List<PostVO> convertPostToPostVO(Long communityId, Integer offset, Integer size);

    Pager<PostVO> searchByKeyword(String keyword, Integer page, Integer size, SortBy sortBy);

    PostVO getPostVOById(Long id);

    PostVO createPost(Long userId, CreatePostDto createPostDto);

    void deletePost(Long userId, Long postId);

    void deletePostsByCommunityId(Long communityId);
}
