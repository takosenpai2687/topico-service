package com.topico.web;

import com.topico.dto.UpdateUserDto;
import com.topico.entity.User;
import com.topico.pojo.Response;
import com.topico.dto.CreateUserDto;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import com.topico.service.UserService;

@RestController
@RequestMapping("api/v1/users")
public class UserController {

    private final UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/{id}")
    public Response<User> findUserById(@PathVariable("id") @Valid Long id) {
        final User user = userService.findUserById(id);
        return Response.success(user);
    }

    @PostMapping()
    public Response<User> createUser(@RequestBody @Valid CreateUserDto createUserDTO) {
        final User user = userService.createUser(createUserDTO);
        return Response.success(user);
    }

    @DeleteMapping("/{id}")
    public Response<User> deleteUser(@PathVariable("id") @Valid Long id) {
        User deletedUser = userService.deleteUserById(id);
        return Response.success(deletedUser);
    }

    @PutMapping("/{id}")
    public Response<User> updateUser(@PathVariable("id") Long id, @RequestBody @Valid UpdateUserDto updateUserDto) {
        updateUserDto.setId(id);
        User updatedUser = userService.updateUser(updateUserDto);
        return Response.success(updatedUser);
    }

}
