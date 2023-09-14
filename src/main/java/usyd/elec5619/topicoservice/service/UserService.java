package usyd.elec5619.topicoservice.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import usyd.elec5619.topicoservice.dto.user.UpdatePasswordDto;
import usyd.elec5619.topicoservice.dto.user.UpdateUserDto;
import usyd.elec5619.topicoservice.model.User;

import java.util.List;


@Service
public interface UserService {

    Long emailToId(String email);

    void deleteUser(Long id);

    User updateUser(Long id, UpdateUserDto user);

    void updatePassword(Long id, UpdatePasswordDto updatePasswordDto);

    User getUserByEmail(String email);

    UserDetailsService userDetailsService();

    List<User> getAllUsers();


    User getUserById(Long id);
}
