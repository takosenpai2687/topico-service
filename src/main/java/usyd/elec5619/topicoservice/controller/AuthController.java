package usyd.elec5619.topicoservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import usyd.elec5619.topicoservice.dto.auth.LoginDto;
import usyd.elec5619.topicoservice.dto.auth.SignupDto;
import usyd.elec5619.topicoservice.pojo.CommonResponse;
import usyd.elec5619.topicoservice.service.AuthService;
import usyd.elec5619.topicoservice.vo.LoginVO;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/signup")
    public CommonResponse<LoginVO> signup(@RequestBody SignupDto signupDto) {
        return CommonResponse.success(authService.signup(signupDto));
    }

    @PostMapping("/login")
    public CommonResponse<LoginVO> login(@RequestBody LoginDto loginDto) {
        return CommonResponse.success(authService.login(loginDto));
    }
}
