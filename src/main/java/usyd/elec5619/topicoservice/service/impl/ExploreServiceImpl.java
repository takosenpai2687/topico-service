package usyd.elec5619.topicoservice.service.impl;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import usyd.elec5619.topicoservice.model.Community;
import usyd.elec5619.topicoservice.service.CommunityService;
import usyd.elec5619.topicoservice.service.ExploreService;
import usyd.elec5619.topicoservice.service.PostService;
import usyd.elec5619.topicoservice.service.RedisService;
import usyd.elec5619.topicoservice.type.SortBy;
import usyd.elec5619.topicoservice.vo.Pager;
import usyd.elec5619.topicoservice.vo.PostVO;
import usyd.elec5619.topicoservice.vo.SearchResultVO;

import java.util.List;
import java.util.Map;

@Service
@EnableScheduling
public class ExploreServiceImpl implements ExploreService {

    private final CommunityService communityService;
    private final PostService postService;

    private final RedisService redisService;

    private List<Community> cachedTopCommunities;

    public ExploreServiceImpl(CommunityService communityService, PostService postService, RedisService redisService) {
        this.communityService = communityService;
        this.postService = postService;
        this.redisService = redisService;
    }


    @Override
    public Map<String, Integer> getTopSearch(Boolean force) {
        if (force) {
            redisService.updateTopSearch();
        }
        return redisService.getTopSearch();
    }

    @Override
    public List<Community> getTopCommunities(Boolean force) {
        if (force) {
            cachedTopCommunities = communityService.getTopCommunities();
        }
        return cachedTopCommunities;
    }

    @Override
    public SearchResultVO search(String keyword, Integer page, Integer size, SortBy sortBy) {
        List<Community> communities = communityService.searchByKeyword(keyword, 5);
        Pager<PostVO> posts = postService.searchByKeyword(keyword, page, size, sortBy);
        redisService.addSearch(keyword);
        return SearchResultVO.builder().communities(communities).posts(posts).sortBy(sortBy).build();
    }

    @Scheduled(fixedRateString = "${scheduled.updateTopCommunities}")
    void updateTopCommunities() {
        cachedTopCommunities = communityService.getTopCommunities();
    }

    @Scheduled(fixedRateString = "${scheduled.updateTopSearch}")
    void updateTopSearch() {
        redisService.updateTopSearch();
    }
}
