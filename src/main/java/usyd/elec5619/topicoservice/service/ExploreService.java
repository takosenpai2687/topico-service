package usyd.elec5619.topicoservice.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import usyd.elec5619.topicoservice.model.Community;
import usyd.elec5619.topicoservice.type.SortBy;
import usyd.elec5619.topicoservice.vo.SearchResultVO;

import java.util.List;
import java.util.Map;

@Service
public interface ExploreService {
    Map<String, Integer> getTopSearch(Boolean force);

    List<Community> getTopCommunities(Boolean force);

    SearchResultVO search(String keyword, Integer page, Integer size, SortBy sortBy);

}
