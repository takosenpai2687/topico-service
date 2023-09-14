package usyd.elec5619.topicoservice.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import usyd.elec5619.topicoservice.mapper.PostMapper;
import usyd.elec5619.topicoservice.model.*;
import usyd.elec5619.topicoservice.service.CommunityService;
import usyd.elec5619.topicoservice.service.PostService;
import usyd.elec5619.topicoservice.service.UserService;
import usyd.elec5619.topicoservice.type.SortBy;
import usyd.elec5619.topicoservice.vo.PostVO;
import usyd.elec5619.topicoservice.vo.PostsVO;
import usyd.elec5619.topicoservice.service.TagService;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostMapper postMapper;
    private final TagService tagService;
    private final UserService userService;
    private final CommunityService communityService;

    @Override
    public PostsVO getPostsByUserId(Long userId, Integer page, Integer size) {
        assert page > -1;
        List<PostVO> posts = postMapper.getPostsByUserId(userId, page * size, size);
        Integer total = postMapper.countPostsByUserId(userId);
        return PostsVO.builder().posts(posts).page(page).size(size).total(total).build();
    }

    @Override
    public PostsVO getPostsByCommunityId(Long communityId, Integer page, Integer size, SortBy sortBy) {
        final List<PostVO> posts = sortBy.equals(SortBy.MOST_LIKES) ?
                postMapper.getHotPostsByCommunityId(communityId, page, size) :
                postMapper.getNewPostsByCommunityId(communityId, page, size);
        final Integer total = postMapper.countPostsByCommunityId(communityId);
        return PostsVO.builder().posts(posts).page(page).size(size).total(total).build();
    }

}
