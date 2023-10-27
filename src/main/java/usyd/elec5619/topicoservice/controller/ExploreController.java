package usyd.elec5619.topicoservice.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import usyd.elec5619.topicoservice.model.Community;
import usyd.elec5619.topicoservice.pojo.CommonResponse;
import usyd.elec5619.topicoservice.service.ExploreService;
import usyd.elec5619.topicoservice.type.SortBy;
import usyd.elec5619.topicoservice.vo.Pager;
import usyd.elec5619.topicoservice.vo.PostVO;
import usyd.elec5619.topicoservice.vo.SearchResultVO;

import java.util.List;
import java.util.Map;

@RestController()
@RequestMapping("/api/v1/explore")
@RequiredArgsConstructor
public class ExploreController {

    private final ExploreService exploreService;

    @GetMapping("/top_search")
    public CommonResponse<Map<String, Integer>> getTopSearch(@RequestParam(required = false, name = "force") Boolean force) {
        Boolean isForce = force != null && force;
        Map<String, Integer> topSearch = exploreService.getTopSearch(isForce);
        return CommonResponse.success(topSearch);
    }

    @GetMapping("/top_communities")
    public CommonResponse<List<Community>> getTopCommunities(@RequestParam(required = false, name = "force") Boolean force) {
        Boolean isForce = force != null && force;
        List<Community> topCommunities = exploreService.getTopCommunities(isForce);
        return CommonResponse.success(topCommunities);
    }

    @GetMapping("/search")
    public CommonResponse<SearchResultVO> search(@Valid @NotBlank @RequestParam String keyword, @RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer size, @RequestParam(defaultValue = "MOST_LIKES") SortBy sortBy) {
        SearchResultVO searchResultVO = exploreService.search(keyword, page, size, sortBy);
        return CommonResponse.success(searchResultVO);
    }

    @GetMapping("/trending_posts")
    public CommonResponse<Pager<PostVO>> getTrendingPosts(@RequestParam() SortBy sortBy, @RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer size) {
        Pager<PostVO> trendingPosts = exploreService.getTrendingPosts(sortBy, page, size);
        return CommonResponse.success(trendingPosts);
    }

}
