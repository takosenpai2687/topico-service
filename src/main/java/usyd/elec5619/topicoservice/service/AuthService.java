package usyd.elec5619.topicoservice.service;

import org.springframework.stereotype.Service;
import usyd.elec5619.topicoservice.dto.auth.LoginDto;
import usyd.elec5619.topicoservice.dto.auth.SignupDto;
import usyd.elec5619.topicoservice.dto.user.UpdatePasswordDto;
import usyd.elec5619.topicoservice.vo.LoginVO;

@Service
public interface AuthService {
    LoginVO signup(SignupDto signupDto);

    LoginVO login(LoginDto loginDto, String ip);

    void updatePassword(Long id, UpdatePasswordDto updatePasswordDto);
}