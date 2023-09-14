package usyd.elec5619.topicoservice.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import usyd.elec5619.topicoservice.mapper.UserMapper;
import usyd.elec5619.topicoservice.model.UserCommunity;
import usyd.elec5619.topicoservice.pojo.CommonResponse;
import usyd.elec5619.topicoservice.service.CheckinService;
import usyd.elec5619.topicoservice.service.CommunityService;
import usyd.elec5619.topicoservice.service.UserService;

@RestController("/api/v1/communities")
@RequiredArgsConstructor
public class CommunityController {

    private final UserService userService;
    private final CheckinService checkinService;
    private final CommunityService communityService;

    @PostMapping("/checkin/{communityId}")
    public CommonResponse<UserCommunity> checkin(Authentication authentication, @Valid @PathVariable Long communityId) {
        final String email = authentication.getName();
        final Long userId = userService.emailToId(email);
        checkinService.checkin(userId, communityId);
        UserCommunity userCommunity = communityService.getUserCommunity(userId, communityId);
        return CommonResponse.success(userCommunity);
    }

    @PostMapping("/follow/{communityId}")
    public CommonResponse<UserCommunity> follow(Authentication authentication, @Valid @PathVariable Long communityId) {
        final String email = authentication.getName();
        final Long userId = userService.emailToId(email);
        final UserCommunity userCommunity = communityService.follow(userId, communityId);
        return CommonResponse.success(userCommunity);
    }

    @PostMapping("/unfollow/{communityId}")
    public CommonResponse<UserCommunity> unfollow(Authentication authentication, @Valid @PathVariable Long communityId) {
        final String email = authentication.getName();
        final Long userId = userService.emailToId(email);
        final UserCommunity userCommunity = communityService.unfollow(userId, communityId);
        return CommonResponse.success(userCommunity);
    }
}
