package usyd.elec5619.topicoservice.vo;

import jakarta.annotation.Nullable;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import usyd.elec5619.topicoservice.model.Comment;
import usyd.elec5619.topicoservice.model.Community;
import usyd.elec5619.topicoservice.model.Tag;
import usyd.elec5619.topicoservice.model.User;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class PostVO {
    @NonNull Long id;
    @NonNull Community community;
    @NonNull User author;
    @NonNull List<Tag> tags;
    @NonNull String title;
    @NonNull String content;
    @NonNull Boolean spoiler;
    @NonNull List<String> images;
    @NonNull LocalDateTime ctime;
    @NonNull LocalDateTime utime;
    @NonNull Integer likes;
    @NonNull Integer dislikes;
    @NonNull Integer commentsCount;
    List<Comment> comments;
}
