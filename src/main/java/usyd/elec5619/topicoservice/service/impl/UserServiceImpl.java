package usyd.elec5619.topicoservice.service.impl;

import org.springframework.stereotype.Service;
import usyd.elec5619.topicoservice.dto.user.CreateUserDTO;
import usyd.elec5619.topicoservice.exception.http.BadRequestException;
import usyd.elec5619.topicoservice.mapper.UserMapper;
import usyd.elec5619.topicoservice.model.User;
import usyd.elec5619.topicoservice.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    @Override
    public User createUser(CreateUserDTO createUserDTO) {
        String email = createUserDTO.getEmail();
        // Check existing email
        if (userMapper.selectByEmail(email) != null) {
            throw new BadRequestException("Email already exists");
        }
        return null;
    }
}
