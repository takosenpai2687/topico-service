package usyd.elec5619.topicoservice.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import usyd.elec5619.topicoservice.dto.post.CreatePostDto;
import usyd.elec5619.topicoservice.exception.http.InternalException;
import usyd.elec5619.topicoservice.exception.http.NotFoundException;
import usyd.elec5619.topicoservice.mapper.PostMapper;
import usyd.elec5619.topicoservice.model.Post;
import usyd.elec5619.topicoservice.service.ImageService;
import usyd.elec5619.topicoservice.service.PostService;
import usyd.elec5619.topicoservice.service.TagService;
import usyd.elec5619.topicoservice.type.SortBy;
import usyd.elec5619.topicoservice.vo.Pager;
import usyd.elec5619.topicoservice.vo.PostVO;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostMapper postMapper;
    private final TagService tagService;
    private final ImageService imageService;

    @Override
    public Pager<PostVO> getPostsByUserId(Long userId, Integer page, Integer size) {
        assert page > -1;
        final int offset = page * size;
        List<PostVO> posts = postMapper.getPostsByUserId(userId, offset, size);
        Integer total = postMapper.countPostsByUserId(userId);
        return Pager.<PostVO>builder().data(posts).page(page).size(size).total(total).build();
    }

    @Override
    public Pager<PostVO> getPostsByCommunityId(Long communityId, Integer page, Integer size, SortBy sortBy) {
        final int offset = page * size;
        final List<PostVO> posts = sortBy.equals(SortBy.MOST_LIKES) ? postMapper.getHotPostsByCommunityId(communityId, offset, size) : postMapper.getNewPostsByCommunityId(communityId, offset, size);
        final Integer total = postMapper.countPostsByCommunityId(communityId);
        return Pager.<PostVO>builder().data(posts).page(page).size(size).total(total).build();
    }

    @Override
    public Pager<PostVO> searchByKeyword(String keyword, Integer page, Integer size, SortBy sortBy) {
        final int offset = page * size;
        List<PostVO> posts = sortBy.equals(SortBy.MOST_LIKES) ? postMapper.searchHotByKeyword(keyword, offset, size) : postMapper.searchNewByKeyword(keyword, offset, size);
        final Integer total = postMapper.countSearchByTitle(keyword);
        return Pager.<PostVO>builder().data(posts).page(page).size(size).total(total).build();
    }

    @Override
    public PostVO getPostById(Long id) {
        return postMapper.getPostVOById(id).orElseThrow(() -> new NotFoundException("Post not found"));
    }

    @Override
    @Transactional
    public PostVO createPost(Long userId, CreatePostDto createPostDto) {
        Post post = Post.builder()
                .communityId(createPostDto.getCommunityId())
                .authorId(userId)
                .title(createPostDto.getTitle())
                .content(createPostDto.getContent())
                .spoiler(createPostDto.getSpoiler())
                .build();
        Long postId = postMapper.insertOne(post);
        // Add tags
        final List<String> tags = createPostDto.getTags();
        if (tags != null && !tags.isEmpty()) {
            tagService.addTagsToPost(post.getId(), tags);
        }
        // Add images
        final List<String> images = createPostDto.getImages();
        if (images != null && !images.isEmpty()) {
            imageService.addImagesToPost(post.getId(), images);
        }
        return postMapper.getPostVOById(postId).orElseThrow(() -> new InternalException("Failed to create post"));
    }

    @Override
    public void deletePost(Long userId, Long postId) {
        Post post = postMapper.getPostById(postId).orElseThrow(() -> new NotFoundException("Post not found"));
        if (!post.getAuthorId().equals(userId)) {
            throw new NotFoundException("not your post");
        }
        postMapper.deleteOne(postId);
    }

}
