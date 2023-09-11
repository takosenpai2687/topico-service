package usyd.elec5619.topicoservice.service.impl;

import org.springframework.stereotype.Service;
import usyd.elec5619.topicoservice.dto.user.CreateUserDto;
import usyd.elec5619.topicoservice.dto.user.UpdateUserDto;
import usyd.elec5619.topicoservice.exception.http.BadRequestException;
import usyd.elec5619.topicoservice.mapper.UserMapper;
import usyd.elec5619.topicoservice.model.User;
import usyd.elec5619.topicoservice.service.UserService;
import usyd.elec5619.topicoservice.util.PasswordUtil;

@Service
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final PasswordUtil passwordUtil;

    public UserServiceImpl(UserMapper userMapper, PasswordUtil passwordUtil) {
        this.userMapper = userMapper;
        this.passwordUtil = passwordUtil;
    }

    public User createUser(CreateUserDto createUserDTO) {
        // Check existing email
        final String email = createUserDTO.getEmail();
        if (userMapper.getByEmail(email) != null) {
            throw new BadRequestException("Email already exists");
        }
        // Check existing nickName
        final String nickName = createUserDTO.getNickName();
        if (userMapper.getByNickName(nickName) != null) {
            throw new BadRequestException("NickName already exists");
        }
        // Check password
        final String password = createUserDTO.getPassword();
        if (!passwordUtil.isValid(password)) {
            throw new BadRequestException("Password must be 6-16 characters");
        }
        // Create user
        User user = User.builder()
                .email(email)
                .password(passwordUtil.hashPassword(password))
                .build();
        userMapper.insertOne(user);
        return user;
    }

    @Override
    public void deleteUser(Long id) {
        userMapper.deleteById(id);
    }

    @Override
    public User updateUser(Long id, UpdateUserDto updateUserDTO) {
        return userMapper.updateUser(id, updateUserDTO);
    }

    public User getUserByEmail(String email) {
        return userMapper.getByEmail(email);
    }

}
