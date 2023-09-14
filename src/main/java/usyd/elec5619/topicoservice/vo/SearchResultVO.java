package usyd.elec5619.topicoservice.vo;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import usyd.elec5619.topicoservice.model.Community;
import usyd.elec5619.topicoservice.type.SortBy;

import java.util.List;

@Data
@Builder
public class SearchResultVO {
    @NonNull
    private List<Community> communities;
    @NonNull
    private Pager<PostVO> posts;
    @NonNull
    private SortBy sortBy;
}
