package usyd.elec5619.topicoservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import usyd.elec5619.topicoservice.mapper.TagMapper;
import usyd.elec5619.topicoservice.model.Tag;
import usyd.elec5619.topicoservice.service.TagService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TagServiceImpl implements TagService {

    private final TagMapper tagMapper;

    @Override
    public List<Tag> getTagsByPostId(Long postId) {
        return tagMapper.getTagsByPostId(postId);
    }

    @Override
    public List<Tag> getTagsByCommunityId(Long communityId) {
        return tagMapper.getTagsByCommunityId(communityId);
    }

    @Override
    public void addTagsToPost(Long postId, List<String> tags) {
        tagMapper.deleteTagsByPostId(postId);
        tags.forEach(tagName -> {
            Tag dbTag = tagMapper.getTagByName(tagName);
            Long tagId;
            if (dbTag == null) {
                tagId = tagMapper.createTag(tagName);
            } else {
                tagId = dbTag.getId();
            }
            tagMapper.addTagToPost(postId, tagId);
        });
    }
}
