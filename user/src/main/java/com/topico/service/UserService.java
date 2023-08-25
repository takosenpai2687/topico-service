package com.topico.service;

import com.topico.entity.User;
import com.topico.exception.BadRequestException;
import com.topico.exception.NotFoundException;
import com.topico.dto.CreateUserDTO;
import com.topico.dto.UpdateUserDTO;
import com.topico.mapper.UserMapper;
import org.springframework.stereotype.Service;
import com.topico.dao.UserRepo;
import com.topico.util.PasswordUtil;

@Service
public class UserService {
    private final UserRepo userRepo;
    private final PasswordUtil passwordUtil;

    public UserService(UserRepo userRepo, PasswordUtil passwordUtil) {
        this.userRepo = userRepo;
        this.passwordUtil = passwordUtil;
    }

    public User findUserById(Long id) {
        return userRepo.findUserByIdAndDeletedIsFalse(id);
    }

    public User findUserByEmail(String email) {
        return userRepo.findUserByEmailIgnoreCaseAAndDeletedIsFalse(email);
    }

    private User findUserByNickName(String nickName) {
        return userRepo.findUserByNickNameAndDeletedIsFalse(nickName);
    }

    public User createUser(CreateUserDTO createUserDTO) {
        // Check existing email
        final String email = createUserDTO.getEmail().trim().toLowerCase();
        User existingUser = findUserByEmail(email);
        if (existingUser != null) throw new BadRequestException("email already taken");

        // Check existing nickname
        final String nickName = createUserDTO.getNickName().trim();
        existingUser = findUserByNickName(nickName);
        if (existingUser != null) throw new BadRequestException("nick name already taken");

        // Check password
        final String inputPass = createUserDTO.getPassword();
        final String hashedPassword = passwordUtil.hash(inputPass);

        User newUser = User.builder().nickName(nickName).email(email).password(hashedPassword).build();
        userRepo.save(newUser);
        return newUser;
    }

    public User deleteUserById(Long id) {
        User deletedUser = findUserById(id);
        if (deletedUser == null) throw new NotFoundException("user not found");
        deletedUser.setDeleted(true);
        return deletedUser;
    }

    public User updateUser(UpdateUserDTO updateUserDTO) {
        User user = findUserById(updateUserDTO.getId());
        if (user == null) throw new NotFoundException("user not found");
        User updatedUser = UserMapper.INSTANCE.updateFromDTO(updateUserDTO, user);
        userRepo.save(updatedUser);
        return updatedUser;
    }
}
