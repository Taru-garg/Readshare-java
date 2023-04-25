package com.taru.readshare.controller;

import com.taru.readshare.dto.user.UserCreationDTO;
import com.taru.readshare.dto.user.UserDTO;
import com.taru.readshare.dto.user.UserMapper;
import com.taru.readshare.exceptions.UserNotFoundException;
import com.taru.readshare.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    private final UserMapper userMapper;

    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @PostMapping("/register")
    public UserDTO registerUser(@RequestBody UserCreationDTO user) {
        return userMapper.toUserDTO(userService.registerUser(user));
    }

    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable UUID id) {
        userService.deleteUser(id);
    }

    @GetMapping("/get/{id}")
    public UserDTO getUser(@PathVariable UUID id) {
        if(userService.getUser(id).isPresent()) {
            return userMapper.toUserDTO(userService.getUser(id).get());
        } else {
            throw  new UserNotFoundException();
        }
    }
}
