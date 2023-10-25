package usyd.elec5619.topicoservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import usyd.elec5619.topicoservice.pojo.CommonResponse;
import usyd.elec5619.topicoservice.service.NotificationService;
import usyd.elec5619.topicoservice.service.UserService;
import usyd.elec5619.topicoservice.vo.NotificationVO;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/notifications")
public class NotificationController {

    private final NotificationService notificationService;
    private final UserService userService;

    @GetMapping("/")
    public CommonResponse<List<NotificationVO>> getAllNotifications(Authentication authentication) {
        final Long userId = Long.parseLong(authentication.getName());
        final List<NotificationVO> notifications = notificationService.getAllNotifications(userId);
        return CommonResponse.success(notifications);
    }

    @PostMapping("/read/{notificationId}")
    public CommonResponse<Void> readNotification(Authentication authentication, @PathVariable("notificationId") Long notificationId) {
        final Long userId = Long.parseLong(authentication.getName());
        notificationService.readNotification(userId, notificationId);
        return CommonResponse.success();
    }

}
