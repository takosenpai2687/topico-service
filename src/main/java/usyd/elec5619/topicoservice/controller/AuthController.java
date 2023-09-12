package usyd.elec5619.topicoservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import usyd.elec5619.topicoservice.dto.LoginDto;
import usyd.elec5619.topicoservice.dto.user.CreateUserDto;
import usyd.elec5619.topicoservice.pojo.CommonResponse;
import usyd.elec5619.topicoservice.service.AuthService;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/signup")
    public CommonResponse<String> signup(@RequestBody CreateUserDto createUserDto) {
        return CommonResponse.success(authService.signup(createUserDto));
    }

    @PostMapping("/login")
    public CommonResponse<String> login(@RequestBody LoginDto loginDto) {
        return CommonResponse.success(authService.login(loginDto));
    }
}
