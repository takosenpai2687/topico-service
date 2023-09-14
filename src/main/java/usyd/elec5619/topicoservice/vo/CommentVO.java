package usyd.elec5619.topicoservice.vo;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import usyd.elec5619.topicoservice.model.Comment;
import usyd.elec5619.topicoservice.model.User;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class CommentVO {
    @NonNull Long id;
    @NonNull Long postId;
    String postTitle;
    @NonNull User author;
    List<CommentVO> children;
    @NonNull String content;
    @NonNull Integer likes;
    @NonNull Integer dislikes;
    @NonNull Integer replies;
    @NonNull LocalDateTime ctime;
    @NonNull LocalDateTime utime;
}
