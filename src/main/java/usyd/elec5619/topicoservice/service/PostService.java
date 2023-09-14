package usyd.elec5619.topicoservice.service;

import org.springframework.stereotype.Service;
import usyd.elec5619.topicoservice.model.Comment;
import usyd.elec5619.topicoservice.model.Tag;
import usyd.elec5619.topicoservice.type.SortBy;
import usyd.elec5619.topicoservice.vo.Pager;
import usyd.elec5619.topicoservice.vo.PostVO;
import usyd.elec5619.topicoservice.vo.PostsVO;

import java.util.List;

@Service
public interface PostService {
    Pager<PostVO> getPostsByUserId(Long userId, Integer page, Integer size);

    Pager<PostVO> getPostsByCommunityId(Long communityId, Integer page, Integer size, SortBy sortBy);

    Pager<PostVO> searchByTitle(String keyword, Integer page, Integer size, SortBy sortBy);
}
