package usyd.elec5619.topicoservice.vo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CheckinVO {
    private boolean isCheckedToday;
    private int checkinDays;
}
