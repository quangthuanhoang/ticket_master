package com.thuanhq.ticket_master.controller;

import java.util.List;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.thuanhq.ticket_master.dto.request.user.UserCreationRequest;
import com.thuanhq.ticket_master.dto.request.user.UserUpdateRequest;
import com.thuanhq.ticket_master.dto.response.APIResponse;
import com.thuanhq.ticket_master.dto.response.user.UserResponse;
import com.thuanhq.ticket_master.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping()
    public APIResponse<UserResponse> createUser(@RequestBody @Valid UserCreationRequest user) {
        APIResponse<UserResponse> response = new APIResponse<>();
        response.setResult(userService.createUser(user));
        return response;
    }

    @GetMapping()
    public List<UserResponse> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{userId}")
    public UserResponse getUserById(@PathVariable String userId) {
        return userService.getUserById(userId);
    }

    @PutMapping("/{userId}")
    public UserResponse updateUser(@PathVariable String userId, @RequestBody @Valid UserUpdateRequest user) {
        return userService.updateUser(userId, user);
    }

    @DeleteMapping("/{userId}")
    public void deleteUserById(@PathVariable String userId) {
        userService.deleteUserById(userId);
    }
}
