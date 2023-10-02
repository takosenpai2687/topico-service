package usyd.elec5619.topicoservice.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import usyd.elec5619.topicoservice.dto.post.CreatePostDto;
import usyd.elec5619.topicoservice.exception.http.NotFoundException;
import usyd.elec5619.topicoservice.mapper.CommunityMapper;
import usyd.elec5619.topicoservice.mapper.PostMapper;
import usyd.elec5619.topicoservice.mapper.UserMapper;
import usyd.elec5619.topicoservice.model.Post;
import usyd.elec5619.topicoservice.model.User;
import usyd.elec5619.topicoservice.service.CommentService;
import usyd.elec5619.topicoservice.service.ImageService;
import usyd.elec5619.topicoservice.service.NotificationService;
import usyd.elec5619.topicoservice.service.PostService;
import usyd.elec5619.topicoservice.type.SortBy;
import usyd.elec5619.topicoservice.vo.Pager;
import usyd.elec5619.topicoservice.vo.PostVO;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostMapper postMapper;
    private final ImageService imageService;
    private final UserMapper userMapper;
    private final CommentService commentService;
    private final NotificationService notificationService;

    private final CommunityMapper communityMapper;

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
    public PostVO getPostVOById(Long postId) {
        Post post = postMapper.getPostById(postId).orElseThrow(() -> new NotFoundException("Post not found"));
        User author = userMapper.getUserById(post.getAuthorId()).orElseThrow(() -> new NotFoundException("Author not found"));
        return PostVO.builder()
                     .id(postId)
                     .community(communityMapper.getCommunityByPostId(post.getId()))
                     .author(author)
                     .title(post.getTitle())
                     .content(post.getContent())
                     .spoiler(post.getSpoiler())
                     .tags(post.getTags())
                     .images(imageService.getImagesByPostId(postId))
                     .ctime(post.getCtime())
                     .utime(post.getUtime())
                     .likes(post.getLikes())
                     .dislikes(post.getDislikes())
                     .replies(post.getReplies())
                     .build();
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
                        .tags(createPostDto.getTags())
                        .build();
        postMapper.insertOne(post);
        final Long postId = post.getId();
        // Add images
        final List<Long> images = createPostDto.getImages();
        if (images != null && !images.isEmpty()) {
            imageService.addImagesToPost(post.getId(), images);
        }
        return this.getPostVOById(postId);
    }

    @Override
    @Transactional
    public void deletePost(Long userId, Long postId) {
        Post post = postMapper.getPostById(postId).orElseThrow(() -> new NotFoundException("Post not found"));
        if (!post.getAuthorId().equals(userId)) {
            throw new NotFoundException("not your post");
        }
        deletePostById(postId);
    }

    @Override
    @Transactional
    public void deletePostsByCommunityId(Long communityId) {
        List<Post> posts = postMapper.getPostsByCommunityId(communityId);
        posts.forEach(post -> deletePostById(post.getId()));
    }


    private void deletePostById(Long postId) {
        // delete post images
        imageService.deleteImagesByPostId(postId);
        // delete post like notifications
        notificationService.deleteAllNotificationsByPostId(postId);
        // delete post likes
        postMapper.deleteAllLikesByPostId(postId);
        // delete comments
        commentService.deleteAllCommentsByPostId(postId);
        postMapper.deleteOne(postId);
    }

}


//  posts.parallelStream().forEach(post -> {