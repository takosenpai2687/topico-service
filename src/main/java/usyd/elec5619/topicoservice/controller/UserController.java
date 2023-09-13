package usyd.elec5619.topicoservice.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import usyd.elec5619.topicoservice.dto.user.UpdatePasswordDto;
import usyd.elec5619.topicoservice.dto.user.UpdateUserDto;
import usyd.elec5619.topicoservice.model.Community;
import usyd.elec5619.topicoservice.model.User;
import usyd.elec5619.topicoservice.pojo.CommonResponse;
import usyd.elec5619.topicoservice.service.JwtService;
import usyd.elec5619.topicoservice.service.UserService;
import usyd.elec5619.topicoservice.vo.PostVO;
import usyd.elec5619.topicoservice.vo.PostsVO;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    @GetMapping
    public CommonResponse<List<User>> getAllUsers() {
        List<User> allUsers = userService.getAllUsers();
        return CommonResponse.success(allUsers);
    }

    @PutMapping("/{id}")
    public CommonResponse<User> updateUser(@PathVariable @Valid Long id, @Valid @RequestBody UpdateUserDto updateUserDTO) {
        User updatedUser = userService.updateUser(id, updateUserDTO);
        return CommonResponse.success(updatedUser);
    }

    @PutMapping("/password/{id}")
    public CommonResponse<?> updatePassword(@PathVariable @Valid Long id, @Valid @RequestBody UpdatePasswordDto updatePasswordDto) {
        userService.updatePassword(id, updatePasswordDto);
        return CommonResponse.success();
    }

    @GetMapping("/test")
    public CommonResponse<Authentication> getCredential(Authentication authentication) {
        String email = authentication.getName();
        return CommonResponse.success(authentication);
    }

}
