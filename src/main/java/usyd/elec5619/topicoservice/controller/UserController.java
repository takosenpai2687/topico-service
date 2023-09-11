package usyd.elec5619.topicoservice.controller;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import usyd.elec5619.topicoservice.dto.user.CreateUserDto;
import usyd.elec5619.topicoservice.dto.user.UpdateUserDto;
import usyd.elec5619.topicoservice.model.User;
import usyd.elec5619.topicoservice.pojo.CommonResponse;
import usyd.elec5619.topicoservice.service.UserService;

@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/users")
    public CommonResponse<User> createUser(@Valid @RequestBody CreateUserDto createUserDTO) {
        User user = userService.createUser(createUserDTO);
        return CommonResponse.success(user);
    }

    @PutMapping("/users/{id}")
    public CommonResponse<User> updateUser(@Valid Long id, @Valid @RequestBody UpdateUserDto updateUserDTO) {
        User updatedUser = userService.updateUser(id, updateUserDTO);
        return CommonResponse.success(updatedUser);
    }

}
