package usyd.elec5619.topicoservice.service;

import org.springframework.stereotype.Service;
import usyd.elec5619.topicoservice.model.Community;
import usyd.elec5619.topicoservice.type.SortBy;
import usyd.elec5619.topicoservice.vo.SearchResultVO;

import java.util.List;

@Service
public interface ExploreService {
    List<String> getTopSearch();

    List<Community> getTopCommunities();

    SearchResultVO search(String keyword, Integer page, Integer size, SortBy sortBy);
}
