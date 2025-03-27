package com.thuanhq.ticket_master.controller;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import com.thuanhq.ticket_master.dto.request.user.UserCreationRequest;
import com.thuanhq.ticket_master.dto.request.user.UserUpdateRequest;
import com.thuanhq.ticket_master.dto.response.APIResponse;
import com.thuanhq.ticket_master.dto.response.PageResponse;
import com.thuanhq.ticket_master.dto.response.user.UserResponse;
import com.thuanhq.ticket_master.service.UserService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping()
    public APIResponse<UserResponse> createUser(@RequestBody @Valid UserCreationRequest user) {
        return APIResponse.<UserResponse>builder()
                .result(userService.createUser(user))
                .build();
    }

    @GetMapping()
    public APIResponse<PageResponse<UserResponse>> getAllUsers(
            @RequestParam(value = "pageSize", required = false) Integer pageSize,
            @RequestParam(value = "currentPage", required = false) Integer currentPage) {

        var authentication = SecurityContextHolder.getContext().getAuthentication();
        log.info("Username {}", authentication.getName());
        authentication.getAuthorities().forEach(s -> log.info(s.getAuthority()));

        return APIResponse.<PageResponse<UserResponse>>builder()
                .result(userService.getAllUsers(pageSize, currentPage))
                .build();
    }

    @GetMapping("/{userId}")
    public APIResponse<UserResponse> getUserById(@PathVariable String userId) {
        return APIResponse.<UserResponse>builder()
                .result(userService.getUserById(userId))
                .build();
    }

    @GetMapping("/myInfo")
    public APIResponse<UserResponse> getMyinfo() {
        return APIResponse.<UserResponse>builder()
                .result(userService.getMyInfo())
                .build();
    }

    @PutMapping("/{userId}")
    public APIResponse<UserResponse> updateUser(
            @PathVariable String userId, @RequestBody @Valid UserUpdateRequest user) {
        return APIResponse.<UserResponse>builder()
                .result(userService.updateUser(userId, user))
                .build();
    }

    @DeleteMapping("/{userId}")
    public void deleteUserById(@PathVariable String userId) {
        userService.deleteUserById(userId);
    }
}
