package usyd.elec5619.topicoservice.service;

import usyd.elec5619.topicoservice.dto.LoginDto;
import usyd.elec5619.topicoservice.dto.user.CreateUserDto;

public interface AuthService {
    String signup(CreateUserDto createUserDto);

    String login(LoginDto loginDto);
}