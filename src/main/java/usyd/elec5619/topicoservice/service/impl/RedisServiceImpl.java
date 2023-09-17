package usyd.elec5619.topicoservice.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;
import usyd.elec5619.topicoservice.service.RedisService;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class RedisServiceImpl implements RedisService {

    @Value("${spring.data.redis.top-search-key}")
    private String TOP_SEARCH_KEY;

    private final RedisTemplate<String, Object> redisTemplate;

    private Map<String, Integer> cachedTopSearch;

    public RedisServiceImpl(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }


    @Override
    public void addSearch(String keyword) {
        redisTemplate.opsForZSet().incrementScore(TOP_SEARCH_KEY, keyword, 1);
    }


    @Override
    public void updateTopSearch() {
        Set<ZSetOperations.TypedTuple<Object>> typedTuples = redisTemplate.opsForZSet().reverseRangeWithScores(TOP_SEARCH_KEY, 0, 9);
        if (typedTuples == null) {
            cachedTopSearch = Collections.emptyMap();
            return;
        }
        cachedTopSearch = typedTuples.stream()
                                     .collect(Collectors.toMap(
                                             val -> Objects.requireNonNull(val.getValue()).toString(),
                                             tuple -> Objects.requireNonNull(tuple.getScore()).intValue(),
                                             (a, b) -> a,
                                             LinkedHashMap::new
                                     ));
    }

    public Map<String, Integer> getTopSearch() {
        return cachedTopSearch;
    }

}
