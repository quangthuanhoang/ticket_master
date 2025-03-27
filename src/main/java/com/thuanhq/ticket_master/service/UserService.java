package com.thuanhq.ticket_master.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.thuanhq.ticket_master.common.PagingUtils;
import com.thuanhq.ticket_master.dto.request.user.UserCreationRequest;
import com.thuanhq.ticket_master.dto.request.user.UserUpdateRequest;
import com.thuanhq.ticket_master.dto.response.PageResponse;
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

        return userMapper.toUserResponse(userRepository.save(user));
    }

    //    public PageResponse<UserResponse> getAllUsers(Integer pageSize, Integer currentPage) {
    //        int resolvedPageSize = pageSize != null ? pageSize : Integer.MAX_VALUE;
    //        int resolvedCurrentPage = currentPage != null ? currentPage : 0;
    //
    //        Sort sort = Sort.by(Sort.Direction.DESC, "createdAt");
    //        Pageable pageable = PageRequest.of(resolvedCurrentPage, resolvedPageSize, sort);
    //
    //        Page<User> users = userRepository.findAll(pageable);
    //        List<UserResponse> userResponseResponseList =
    //                users.getContent().stream().map(userMapper::toUserResponse).toList();
    //
    //        return PageResponse.<UserResponse>builder()
    //                .currentPage(resolvedCurrentPage)
    //                .pageSize(pageable.getPageSize())
    //                .totalElements(users.getTotalElements())
    //                .totalPages(users.getTotalPages())
    //                .data(userResponseResponseList)
    //                .build();
    //    }

    public PageResponse<UserResponse> getAllUsers(Integer pageSize, Integer currentPage) {

        Pageable pageable =
                PagingUtils.resolvePageable(currentPage, pageSize, Sort.by(Sort.Direction.ASC, "createdAt"));
        Page<User> users = userRepository.findAll(pageable);

        return PagingUtils.toPageResponse(users, userMapper::toUserResponse);
    }

    public UserResponse getUserById(String id) {
        return userMapper.toUserResponse(
                userRepository.findById(id).orElseThrow(() -> new ApplicationException(ErrorCode.USER_NOT_EXISTED)));
    }

    public void deleteUserById(String id) {
        userRepository.deleteById(id);
    }

    public UserResponse updateUser(String userId, UserUpdateRequest request) {
        User user =
                userRepository.findById(userId).orElseThrow(() -> new ApplicationException(ErrorCode.USER_NOT_EXISTED));

        userMapper.updateUser(user, request);

        return userMapper.toUserResponse(userRepository.save(user));
    }
}
