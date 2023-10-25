package usyd.elec5619.topicoservice.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import usyd.elec5619.topicoservice.dto.user.UpdateUserDto;
import usyd.elec5619.topicoservice.exception.http.NotFoundException;
import usyd.elec5619.topicoservice.mapper.UserMapper;
import usyd.elec5619.topicoservice.model.User;
import usyd.elec5619.topicoservice.service.UserService;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    @Override
    public Long emailToId(String email) {
        return userMapper.emailToId(email).orElseThrow(() -> new NotFoundException("user not found"));
    }

    @Override
    public void deleteUser(Long id) {
        userMapper.deleteById(id);
    }

    @Override
    public User updateUser(Long id, UpdateUserDto updateUserDTO) {
        String nickName = updateUserDTO.getNickName();
        if (nickName != null) {
            User userWithNewNickName = userMapper.getByNickName(nickName);
            if (userWithNewNickName != null && !userWithNewNickName.getId().equals(id)) {
                throw new IllegalArgumentException("nickname already exists");
            }
        }
        Long avatarId = updateUserDTO.getAvatar();
        if (avatarId != null && avatarId <= 0) {
            updateUserDTO.setAvatar(null);
        }
        userMapper.updateUser(id, updateUserDTO);
        return getUserById(id);
    }

    public User getUserByEmail(String email) {
        return userMapper.getByEmail(email).orElseThrow(() -> new NotFoundException("user not found"));
    }

    @Override
    public UserDetailsService userDetailsService() {
        return userIdStrOrEmail -> {
            try {
                Long userId = Long.parseLong(userIdStrOrEmail);
                return userMapper.getUserById(userId).orElseThrow(() -> new NotFoundException("user not found"));
            } catch (NumberFormatException e) {
                return userMapper.getByEmail(userIdStrOrEmail).orElseThrow(() -> new NotFoundException("user not found"));
            }
        };
    }

    @Override
    public List<User> getAllUsers() {
        return Objects.requireNonNullElse(userMapper.getAllUsers(), Collections.emptyList());
    }


    @Override
    public User getUserById(Long id) {
        return userMapper.getUserById(id).orElseThrow(() -> new NotFoundException("user not found"));
    }

}
