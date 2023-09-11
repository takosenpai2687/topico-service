package usyd.elec5619.topicoservice.service;

import org.springframework.stereotype.Service;
import usyd.elec5619.topicoservice.dto.user.CreateUserDto;
import usyd.elec5619.topicoservice.dto.user.UpdatePasswordDto;
import usyd.elec5619.topicoservice.dto.user.UpdateUserDto;
import usyd.elec5619.topicoservice.model.User;


@Service
public interface UserService {
    User createUser(CreateUserDto createUserDTO);

    void deleteUser(Long id);

    User updateUser(Long id, UpdateUserDto user);

    void updatePassword(Long id, UpdatePasswordDto updatePasswordDto);

    User getUserByEmail(String email);
}
