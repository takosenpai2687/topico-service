package usyd.elec5619.topicoservice.service;

import org.springframework.stereotype.Service;
import usyd.elec5619.topicoservice.model.Tag;

import java.util.List;

@Service
public interface TagService {
    List<Tag> getTagsByPostId(Long postId);

    List<Tag> getTagsByCommunityId(Long communityId);

}
