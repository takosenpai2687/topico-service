package usyd.elec5619.topicoservice.service.impl;

import org.springframework.stereotype.Service;
import usyd.elec5619.topicoservice.model.Community;
import usyd.elec5619.topicoservice.service.CommunityService;
import usyd.elec5619.topicoservice.service.ExploreService;
import usyd.elec5619.topicoservice.service.PostService;
import usyd.elec5619.topicoservice.type.SortBy;
import usyd.elec5619.topicoservice.vo.Pager;
import usyd.elec5619.topicoservice.vo.PostVO;
import usyd.elec5619.topicoservice.vo.SearchResultVO;

import java.util.List;

@Service
public class ExploreServiceImpl implements ExploreService {

    CommunityService communityService;
    PostService postService;

    @Override
    public List<String> getTopSearch() {
        return null;
    }

    @Override
    public List<Community> getTopCommunities() {
        return null;
    }

    @Override
    public SearchResultVO search(String keyword, Integer page, Integer size, SortBy sortBy) {
        List<Community> communities = communityService.searchByKeyword(keyword, 5);
        Pager<PostVO> posts = postService.searchByKeyword(keyword, page, size, sortBy);
        return SearchResultVO.builder().communities(communities).posts(posts).sortBy(sortBy).build();
    }
}
