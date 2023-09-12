package usyd.elec5619.topicoservice.service;

import usyd.elec5619.topicoservice.dto.auth.LoginDto;
import usyd.elec5619.topicoservice.dto.auth.SignupDto;

public interface AuthService {
    String signup(SignupDto signupDto);

    String login(LoginDto loginDto);
}