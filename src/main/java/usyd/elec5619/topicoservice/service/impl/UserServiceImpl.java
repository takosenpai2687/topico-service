package usyd.elec5619.topicoservice.service.impl;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import usyd.elec5619.topicoservice.dto.user.UpdatePasswordDto;
import usyd.elec5619.topicoservice.dto.user.UpdateUserDto;
import usyd.elec5619.topicoservice.exception.http.BadRequestException;
import usyd.elec5619.topicoservice.exception.http.ForbiddenException;
import usyd.elec5619.topicoservice.exception.http.NotFoundException;
import usyd.elec5619.topicoservice.mapper.UserMapper;
import usyd.elec5619.topicoservice.model.User;
import usyd.elec5619.topicoservice.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }


    @Override
    public void deleteUser(Long id) {
        userMapper.deleteById(id);
    }

    @Override
    public User updateUser(Long id, UpdateUserDto updateUserDTO) {
        return userMapper.updateUser(id, updateUserDTO);
    }

    @Override
    public void updatePassword(Long id, UpdatePasswordDto updatePasswordDto) {
//        // Check new password
//        if (!passwordUtil.isValid(updatePasswordDto.getNewPassword())) {
//            throw new BadRequestException("Password must be 6-16 characters");
//        }
//        // Check user exists
//        final User dbUser = userMapper.getUserById(id);
//        if (dbUser == null) {
//            throw new ForbiddenException("User not found");
//        }
//        // Check old password
//        if (passwordUtil.isCorrect(updatePasswordDto.getOldPassword(), dbUser.getPassword())) {
//            throw new ForbiddenException("Old password is incorrect");
//        }
//        // Update password
//        userMapper.updateUserPassword(id, passwordUtil.hashPassword(updatePasswordDto.getNewPassword()));
    }

    public User getUserByEmail(String email) {
        return userMapper.getByEmail(email).orElseThrow(() -> new NotFoundException("user not found"));
    }

    @Override
    public UserDetailsService userDetailsService() {
        return email -> userMapper.getByEmail(email).orElseThrow(() -> new NotFoundException("User not found"));
    }

    public User getUserById(Long id) {
        return userMapper.getUserById(id);
    }

}
