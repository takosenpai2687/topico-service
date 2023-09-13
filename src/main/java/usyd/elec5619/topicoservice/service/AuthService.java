package usyd.elec5619.topicoservice.service;

import usyd.elec5619.topicoservice.dto.auth.LoginDto;
import usyd.elec5619.topicoservice.dto.auth.SignupDto;
import usyd.elec5619.topicoservice.vo.LoginVO;

public interface AuthService {
    LoginVO signup(SignupDto signupDto);

    LoginVO login(LoginDto loginDto);
}