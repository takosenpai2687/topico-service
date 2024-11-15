package usyd.elec5619.topicoservice.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
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

    @PutMapping()
    public CommonResponse<User> updateUser(Authentication authentication, @Valid @RequestBody UpdateUserDto updateUserDTO) {
        final Long userId = Long.parseLong(authentication.getName());
        User updatedUser = userService.updateUser(userId, updateUserDTO);
        return CommonResponse.success(updatedUser);
    }

}
