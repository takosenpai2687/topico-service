package usyd.elec5619.topicoservice.vo;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Pager<T> {
    private Integer page;
    private Integer size;
    private Integer total;
    private List<T> data;
}
