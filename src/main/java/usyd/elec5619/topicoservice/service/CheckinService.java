package usyd.elec5619.topicoservice.service;

import org.springframework.stereotype.Service;
import usyd.elec5619.topicoservice.vo.CheckinVO;

import java.time.LocalDateTime;
import java.util.List;

@Service
public interface CheckinService {
    void checkin(Long userId, Long communityId);

    List<Boolean> getCheckinRecordsThisMonth(Long userId, Long communityId);

    void checkinForAll(Long userId);

    CheckinVO getCheckin(Long userId, Long communityId);
}
