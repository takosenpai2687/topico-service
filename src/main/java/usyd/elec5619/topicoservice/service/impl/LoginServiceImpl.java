package usyd.elec5619.topicoservice.service.impl;

import usyd.elec5619.topicoservice.dto.LoginDto;
import usyd.elec5619.topicoservice.model.User;
import usyd.elec5619.topicoservice.service.LoginService;
import usyd.elec5619.topicoservice.service.UserService;
import usyd.elec5619.topicoservice.util.PasswordUtil;
import usyd.elec5619.topicoservice.vo.LoginVO;

public class LoginServiceImpl implements LoginService {


    private final UserService userService;
    private final PasswordUtil passwordUtil;

    public LoginServiceImpl(UserService userService, PasswordUtil passwordUtil) {
        this.userService = userService;
        this.passwordUtil = passwordUtil;
    }
     
    @Override
    public LoginVO login(LoginDto loginDto) {
        User dbUser = userService.getUserByEmail(loginDto.getEmail());
        if (dbUser == null) {
            throw new RuntimeException("User not found");
        }
        if (!passwordUtil.isCorrect(loginDto.getPassword(), dbUser.getPassword())) {
            throw new RuntimeException("Password is incorrect");
        }
        return LoginVO.builder()
                .email(dbUser.getEmail())
                .nickName(dbUser.getNickName())
                .build();
    }
}
