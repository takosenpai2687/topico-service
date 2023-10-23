package usyd.elec5619.topicoservice.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import usyd.elec5619.topicoservice.dto.auth.LoginDto;
import usyd.elec5619.topicoservice.dto.auth.SignupDto;
import usyd.elec5619.topicoservice.dto.user.UpdatePasswordDto;
import usyd.elec5619.topicoservice.pojo.CommonResponse;
import usyd.elec5619.topicoservice.service.AuthService;
import usyd.elec5619.topicoservice.service.UserService;
import usyd.elec5619.topicoservice.vo.LoginVO;

@RestController
@RequestMapping("/api/v1/auth")
@Slf4j
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final UserService userService;

    @PostMapping("/signup")
    public CommonResponse<LoginVO> signup(@RequestBody SignupDto signupDto) {
        return CommonResponse.success(authService.signup(signupDto));
    }

    @PostMapping("/login")
    public CommonResponse<LoginVO> login(@RequestBody LoginDto loginDto, HttpServletRequest request) {
        final String clientIp = request.getRemoteAddr();
        return CommonResponse.success(authService.login(loginDto, clientIp));
    }


    @PutMapping("/password")
    public CommonResponse<?> updatePassword(Authentication authentication, @Valid @RequestBody UpdatePasswordDto updatePasswordDto) {
        final String email = authentication.getName();
        final Long id = userService.emailToId(email);
        authService.updatePassword(id, updatePasswordDto);
        return CommonResponse.success();
    }

}
