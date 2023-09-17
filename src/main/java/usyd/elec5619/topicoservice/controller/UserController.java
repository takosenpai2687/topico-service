package usyd.elec5619.topicoservice.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import usyd.elec5619.topicoservice.dto.user.UpdatePasswordDto;
import usyd.elec5619.topicoservice.dto.user.UpdateUserDto;
import usyd.elec5619.topicoservice.model.User;
import usyd.elec5619.topicoservice.pojo.CommonResponse;
import usyd.elec5619.topicoservice.service.UserService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    @GetMapping("/")
    public CommonResponse<List<User>> getAllUsers() {
        List<User> allUsers = userService.getAllUsers();
        return CommonResponse.success(allUsers);
    }

    @GetMapping("/{email}")
    public CommonResponse<User> getUserById(@PathVariable @Valid String email) {
        User user = userService.getUserByEmail(email);
        return CommonResponse.success(user);
    }

    @PutMapping("/{id}")
    public CommonResponse<User> updateUser(@PathVariable @Valid Long id, @Valid @RequestBody UpdateUserDto updateUserDTO) {
        User updatedUser = userService.updateUser(id, updateUserDTO);
        return CommonResponse.success(updatedUser);
    }


    @GetMapping("/test")
    public CommonResponse<Authentication> getCredential(Authentication authentication) {
        String email = authentication.getName();
        return CommonResponse.success(authentication);
    }

}
