package usyd.elec5619.topicoservice.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import usyd.elec5619.topicoservice.exception.http.BadRequestException;
import usyd.elec5619.topicoservice.mapper.CheckinMapper;
import usyd.elec5619.topicoservice.model.UserCommunity;
import usyd.elec5619.topicoservice.service.CheckinService;
import usyd.elec5619.topicoservice.util.BitUtil;
import usyd.elec5619.topicoservice.util.LevelUtil;
import usyd.elec5619.topicoservice.vo.CheckinVO;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

@Service
@Slf4j
@RequiredArgsConstructor
public class CheckinServiceImpl implements CheckinService {

    private final CheckinMapper checkinMapper;

    @Override
    @Transactional
    public void checkin(Long userId, Long communityId) {
        // Get checkin bit map
        UserCommunity userCommunity = checkinMapper.getUserCommunity(userId, communityId);
        Integer checkinBitMap = userCommunity.getCheckin();
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
        userCommunity.setCheckin(checkinBitMap);
        final int exp = userCommunity.getExp() + LevelUtil.CHECKIN_EXP;
        userCommunity.setExp(exp);
        userCommunity.setLevel(LevelUtil.expToLevel(exp));
        // Update checkin bit map
        checkinMapper.checkin(userCommunity);
    }

    private boolean isCheckedInAtDay(Integer checkinBitMap, int dayIdx) {
        return BitUtil.isBitSet(checkinBitMap, dayIdx);
    }

    @Override
    @Transactional
    public void checkinForAll(Long userId) {
        // Get checkin bit maps
        List<UserCommunity> userCommunities = checkinMapper.getUserCommunities(userId);
        if (userCommunities == null || userCommunities.isEmpty()) {
            throw new BadRequestException("You are not a member of any community");
        }
        // Get today idx
        int todayIdx = LocalDate.now().getDayOfMonth() - 1;
        // Update checkin bit maps and exp
        AtomicInteger checkinCount = new AtomicInteger();
        userCommunities.parallelStream()
                       .filter(userCommunity -> !BitUtil.isBitSet(userCommunity.getCheckin(), todayIdx))
                       .forEach(userCommunity -> {
                           final int newExp = userCommunity.getExp() + LevelUtil.CHECKIN_EXP;
                           userCommunity.setExp(newExp);
                           userCommunity.setLevel(LevelUtil.expToLevel(newExp));
                           userCommunity.setCheckin(BitUtil.setBit(userCommunity.getCheckin(), todayIdx));
                           checkinMapper.updateUserCommunity(userCommunity);
                           checkinCount.incrementAndGet();
                       });
        if (checkinCount.get() == 0) {
            throw new BadRequestException("You have already checked in today");
        }
    }

    @Override
    public CheckinVO getCheckin(Long userId, Long communityId) {
        UserCommunity userCommunity = checkinMapper.getUserCommunity(userId, communityId);
        if (userCommunity == null) {
            throw new BadRequestException("You are not a member of this community");
        }
        final int checkinBitmap = userCommunity.getCheckin();
        int todayIdx = LocalDate.now().getDayOfMonth() - 1;
        final int checkinDays = BitUtil.countBitsBeforeIth(checkinBitmap, todayIdx);
        return CheckinVO.builder().isCheckedToday(isCheckedInAtDay(checkinBitmap, todayIdx)).checkinDays(checkinDays).build();
    }


    @Override
    public List<Boolean> getCheckinRecordsThisMonth(Long userId, Long communityId) {
        // Get checkin bit map
        UserCommunity userCommunity = checkinMapper.getUserCommunity(userId, communityId);
        Integer checkinBitMap = userCommunity.getCheckin();
        if (checkinBitMap == null) {
            throw new BadRequestException("You are not a member of this community");
        }
        int days = LocalDate.now().lengthOfMonth();
        return BitUtil.toBooleanList(checkinBitMap, days);
    }

}
