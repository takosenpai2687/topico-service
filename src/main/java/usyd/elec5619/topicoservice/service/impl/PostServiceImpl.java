package usyd.elec5619.topicoservice.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import usyd.elec5619.topicoservice.mapper.PostMapper;
import usyd.elec5619.topicoservice.model.*;
import usyd.elec5619.topicoservice.service.CommunityService;
import usyd.elec5619.topicoservice.service.PostService;
import usyd.elec5619.topicoservice.service.UserService;
import usyd.elec5619.topicoservice.type.SortBy;
import usyd.elec5619.topicoservice.vo.PostVO;
import usyd.elec5619.topicoservice.vo.PostsVO;
import usyd.elec5619.topicoservice.service.TagService;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostMapper postMapper;
    private final TagService tagService;
    private final UserService userService;
    private final CommunityService communityService;

    @Override
    public PostsVO getPostsByUserId(Long userId, Integer page, Integer size) {
        assert page > -1;
        final User user = userService.getUserById(userId);
        List<Post> posts = postMapper.getPostsByUserId(userId, page * size, size);
        Integer total = postMapper.countPostsByUserId(userId);
        List<PostVO> postVOs = posts.stream().map(post -> getPostVO(post, user)).toList();
        return PostsVO.builder().posts(postVOs).page(page).size(size).total(total).build();
    }

    @Override
    public PostsVO getPostsByCommunityId(Long communityId, Integer page, Integer size, SortBy sortBy) {
        final List<Post> posts = sortBy.equals(SortBy.MOST_LIKES) ?
                postMapper.getHotPostsByCommunityId(communityId, page, size) :
                postMapper.getNewPostsByCommunityId(communityId, page, size);
        final Integer total = postMapper.countPostsByCommunityId(communityId);
        final List<PostVO> postVOs = posts.stream()
                                          .map(post -> getPostVO(post, userService.getUserById(post.getAuthorId())))
                                          .toList();
        return PostsVO.builder().posts(postVOs).page(page).size(size).total(total).build();
    }

    private PostVO getPostVO(Post post, User user) {
        Community community = communityService.getCommunityByPostId(post.getId());
        List<Tag> tags = tagService.getTagsByPostId(post.getId());
        List<String> images = postMapper.getImagesByPostId(post.getId());
        Integer likes = postMapper.countLikesByPostId(post.getId());
        Integer dislikes = postMapper.countDislikesByPostId(post.getId());
        Integer commentsCount = postMapper.countCommentsByPostId(post.getId());
        return PostVO.builder()
                     .id(post.getId())
                     .author(user)
                     .community(community)
                     .tags(tags)
                     .title(post.getTitle())
                     .content(post.getContent())
                     .spoiler(post.getSpoiler())
                     .images(images)
                     .ctime(post.getCtime()).utime(post.getUtime())
                     .likes(likes).dislikes(dislikes).
                     commentsCount(commentsCount)
                     .build();
    }
}
