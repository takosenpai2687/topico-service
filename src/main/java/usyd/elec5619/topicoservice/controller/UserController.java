package usyd.elec5619.topicoservice.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import usyd.elec5619.topicoservice.dto.user.UpdatePasswordDto;
import usyd.elec5619.topicoservice.dto.user.UpdateUserDto;
import usyd.elec5619.topicoservice.model.User;
import usyd.elec5619.topicoservice.pojo.CommonResponse;
import usyd.elec5619.topicoservice.service.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

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

}
