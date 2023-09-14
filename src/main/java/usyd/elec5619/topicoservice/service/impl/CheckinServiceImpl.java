package usyd.elec5619.topicoservice.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import usyd.elec5619.topicoservice.exception.http.BadRequestException;
import usyd.elec5619.topicoservice.mapper.CheckinMapper;
import usyd.elec5619.topicoservice.model.UserCommunity;
import usyd.elec5619.topicoservice.service.CheckinService;
import usyd.elec5619.topicoservice.util.BitUtil;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
@Slf4j
@RequiredArgsConstructor
public class CheckinServiceImpl implements CheckinService {

    private final CheckinMapper checkinMapper;

    @Override
    public void checkin(Long userId, Long communityId) {
        // Get checkin bit map
        Integer checkinBitMap = checkinMapper.getCheckinBitMap(userId, communityId);
        if (checkinBitMap == null) {
            throw new BadRequestException("You are not a member of this community");
        }
        // Get today idx
        int todayIdx = LocalDate.now().getDayOfMonth() - 1;
        // Is checked in today?
        if (BitUtil.isBitSet(checkinBitMap, todayIdx)) {
            throw new BadRequestException("You have already checked in today");
        }
        // Set today bit
        checkinBitMap = BitUtil.setBit(checkinBitMap, todayIdx);
        // Update checkin bit map
        checkinMapper.updateCheckinBitMap(userId, communityId, checkinBitMap);
    }

    @Override
    public void checkinForAll(Long userId) {
        // Get checkin bit maps
        List<UserCommunity> userCommunities = checkinMapper.getUserCommunities(userId);
        if (userCommunities == null || userCommunities.isEmpty()) {
            throw new BadRequestException("You are not a member of any community");
        }
        // Get today idx
        int todayIdx = LocalDate.now().getDayOfMonth() - 1;
        // Update checkin bit maps
        AtomicBoolean updated = new AtomicBoolean(false);
        userCommunities.parallelStream().forEach(userCommunity -> {
            Integer checkinBitMap = userCommunity.getCheckin();
            if (BitUtil.isBitSet(checkinBitMap, todayIdx)) {
                return;
            }
            updated.set(true);
            int newCheckinBitMap = BitUtil.setBit(checkinBitMap, todayIdx);
            userCommunity.setCheckin(newCheckinBitMap);
        });
        if (!updated.get()) {
            throw new BadRequestException("You have already checked in today");
        }
        checkinMapper.updateCheckinBitMaps(userCommunities);
    }

    @Override
    public Boolean isCheckedInToday(Long userId, Long communityId) {
        // Get checkin bit map
        Integer checkinBitMap = checkinMapper.getCheckinBitMap(userId, communityId);
        if (checkinBitMap == null) {
            throw new BadRequestException("You are not a member of this community");
        }
        int idx = LocalDate.now().getDayOfMonth() - 1;
        return BitUtil.isBitSet(checkinBitMap, idx);
    }

    @Override
    public List<Boolean> getCheckinRecordsThisMonth(Long userId, Long communityId) {
        // Get checkin bit map
        Integer checkinBitMap = checkinMapper.getCheckinBitMap(userId, communityId);
        if (checkinBitMap == null) {
            throw new BadRequestException("You are not a member of this community");
        }
        int days = LocalDate.now().lengthOfMonth();
        return BitUtil.toBooleanList(checkinBitMap, days);
    }

}
