package usyd.elec5619.topicoservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import usyd.elec5619.topicoservice.model.Community;
import usyd.elec5619.topicoservice.pojo.CommonResponse;
import usyd.elec5619.topicoservice.service.ExploreService;
import usyd.elec5619.topicoservice.type.SortBy;
import usyd.elec5619.topicoservice.vo.SearchResultVO;

import java.util.List;

@RestController("/api/v1/explore")
@RequiredArgsConstructor
public class ExploreController {

    private final ExploreService exploreService;

    @GetMapping("/top_search")
    public CommonResponse<List<String>> getTopSearch() {
        List<String> topSearch = exploreService.getTopSearch();
        return CommonResponse.success(topSearch);
    }

    @GetMapping("/top_communities")
    public CommonResponse<List<Community>> getTopCommunities() {
        List<Community> topCommunities = exploreService.getTopCommunities();
        return CommonResponse.success(topCommunities);
    }

    @GetMapping("/search")
    public CommonResponse<SearchResultVO> search(String keyword,
                                                 @RequestParam(defaultValue = "1") Integer page,
                                                 @RequestParam(defaultValue = "10") Integer size,
                                                 @RequestParam(defaultValue = "MOST_LIKES") SortBy sortBy) {
        SearchResultVO searchResultVO = exploreService.search(keyword, page, size, sortBy);
        return CommonResponse.success(searchResultVO);
    }
}
