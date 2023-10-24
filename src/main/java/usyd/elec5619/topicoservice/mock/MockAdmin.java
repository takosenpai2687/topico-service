package usyd.elec5619.topicoservice.mock;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import usyd.elec5619.topicoservice.dto.auth.SignupDto;
import usyd.elec5619.topicoservice.dto.user.UpdateUserDto;
import usyd.elec5619.topicoservice.model.User;
import usyd.elec5619.topicoservice.service.AdminService;
import usyd.elec5619.topicoservice.service.AuthService;
import usyd.elec5619.topicoservice.service.UserService;
import usyd.elec5619.topicoservice.vo.LoginVO;


@Component
public class MockAdmin {
    private final AuthService authService;
    private final AdminService adminService;
    private final UserService userService;

    @Value("${admin.email}")
    public String adminEmail;
    @Value("${admin.password}")
    public String adminPassword;
    @Value("${admin.nickName}")
    public String adminNickName;

    public MockAdmin(AuthService authService, AdminService adminService, UserService userService) {
        this.authService = authService;
        this.adminService = adminService;
        this.userService = userService;
    }


    public User createAdmin() {
        User existingUser;
        try {
            existingUser = userService.getUserByEmail(adminEmail);
        } catch (Exception e) {
            existingUser = null;
        }
        Long adminId;
        if (existingUser != null) {
            adminId = existingUser.getId();
            UpdateUserDto updateUserDto = UpdateUserDto.builder().nickName(adminNickName).build();
            try {
                userService.updateUser(adminId, updateUserDto);
            } catch (Exception ignored) {
            }
        } else {
            SignupDto superAdmin = SignupDto.builder().email(adminEmail).password(adminPassword).confirmPassword(adminPassword).nickName(adminNickName).build();
            LoginVO adminVO = authService.signup(superAdmin);
            adminId = adminVO.getId();
        }
        adminService.assignAdmin(adminId);
        return userService.getUserById(adminId);
    }
}
