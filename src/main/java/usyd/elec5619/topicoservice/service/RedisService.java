package usyd.elec5619.topicoservice.service;

import org.springframework.stereotype.Service;
import usyd.elec5619.topicoservice.model.Community;

import java.util.List;
import java.util.Map;

@Service
public interface RedisService {

    void addSearch(String search);


    void updateTopSearch();

    Map<String, Integer> getTopSearch();
}
