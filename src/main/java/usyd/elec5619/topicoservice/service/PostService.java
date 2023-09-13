package usyd.elec5619.topicoservice.service;

import usyd.elec5619.topicoservice.model.Comment;
import usyd.elec5619.topicoservice.model.Tag;
import usyd.elec5619.topicoservice.vo.PostVO;
import usyd.elec5619.topicoservice.vo.PostsVO;

import java.util.List;

public interface PostService {
    PostsVO getPostsByUserId(Long userId, Integer page, Integer size);
}
