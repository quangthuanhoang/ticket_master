package com.thuanhq.ticket_master.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.thuanhq.ticket_master.dto.request.user.UserCreationRequest;
import com.thuanhq.ticket_master.dto.request.user.UserUpdateRequest;
import com.thuanhq.ticket_master.dto.response.user.UserResponse;
import com.thuanhq.ticket_master.entity.User;
import com.thuanhq.ticket_master.exception.ApplicationException;
import com.thuanhq.ticket_master.exception.ErrorCode;
import com.thuanhq.ticket_master.mapper.UserMapper;
import com.thuanhq.ticket_master.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    UserMapper userMapper;

    public UserResponse createUser(UserCreationRequest request) {

        if (userRepository.existsByUsername(request.getUsername())) {
            throw new ApplicationException(ErrorCode.USER_EXISTED);
        }

        User user = userMapper.toUser(request);

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userMapper.toUserResponse(userRepository.save(user));
    }

    public List<UserResponse> getAllUsers() {
        return userRepository.findAll().stream().map(userMapper::toUserResponse).toList();
    }

    public UserResponse getUserById(String id) {
        return userMapper.toUserResponse(
                userRepository.findById(id).orElseThrow(() -> new ApplicationException(ErrorCode.USER_NOT_EXISTED)));
    }

    public void deleteUserById(String id) {
        userRepository.deleteById(id);
    }

    public UserResponse updateUser(String userId, UserUpdateRequest request) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ApplicationException(ErrorCode.USER_NOT_EXISTED));
        userMapper.updateUser(user, request);

        return userMapper.toUserResponse(userRepository.save(user));
    }
}
