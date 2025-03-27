package com.thuanhq.ticket_master.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.thuanhq.ticket_master.dto.request.user.UserCreationRequest;
import com.thuanhq.ticket_master.dto.request.user.UserUpdateRequest;
import com.thuanhq.ticket_master.dto.response.user.UserResponse;
import com.thuanhq.ticket_master.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserCreationRequest request);

    @Mapping(target = "roles", ignore = true)
    void updateUser(@MappingTarget User user, UserUpdateRequest request);

    UserResponse toUserResponse(User user);
}
