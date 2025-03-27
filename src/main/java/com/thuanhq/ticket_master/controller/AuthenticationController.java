package com.thuanhq.ticket_master.controller;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.thuanhq.ticket_master.dto.request.auth.LoginRequest;
import com.thuanhq.ticket_master.dto.response.APIResponse;
import com.thuanhq.ticket_master.dto.response.auth.LoginResponse;
import com.thuanhq.ticket_master.service.AuthenticationService;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@RestController
@RequestMapping("/auth")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthenticationController {
    @Autowired
    AuthenticationService authenticationService;

    @PostMapping("/login")
    public APIResponse<LoginResponse> login(@RequestBody @Valid LoginRequest request) {
        return APIResponse.<LoginResponse>builder()
                .result(authenticationService.login(request))
                .build();
    }
}
