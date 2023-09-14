package usyd.elec5619.topicoservice.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import usyd.elec5619.topicoservice.mapper.PostMapper;
import usyd.elec5619.topicoservice.service.CommunityService;
import usyd.elec5619.topicoservice.service.PostService;
import usyd.elec5619.topicoservice.service.TagService;
import usyd.elec5619.topicoservice.service.UserService;
import usyd.elec5619.topicoservice.type.SortBy;
import usyd.elec5619.topicoservice.vo.Pager;
import usyd.elec5619.topicoservice.vo.PostVO;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostMapper postMapper;

    @Override
    public Pager<PostVO> getPostsByUserId(Long userId, Integer page, Integer size) {
        assert page > -1;
        final int offset = page * size;
        List<PostVO> posts = postMapper.getPostsByUserId(userId, offset, size);
        Integer total = postMapper.countPostsByUserId(userId);
        return Pager.<PostVO>builder().data(posts).page(page).size(size).total(total).build();
    }

    @Override
    public Pager<PostVO> getPostsByCommunityId(Long communityId, Integer page, Integer size, SortBy sortBy) {
        final int offset = page * size;
        final List<PostVO> posts = sortBy.equals(SortBy.MOST_LIKES) ? postMapper.getHotPostsByCommunityId(communityId, offset, size) : postMapper.getNewPostsByCommunityId(communityId, offset, size);
        final Integer total = postMapper.countPostsByCommunityId(communityId);
        return Pager.<PostVO>builder().data(posts).page(page).size(size).total(total).build();
    }

}
