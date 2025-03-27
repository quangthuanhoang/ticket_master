package com.thuanhq.ticket_master.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.thuanhq.ticket_master.dto.request.RoleRequest;
import com.thuanhq.ticket_master.dto.response.RoleResponse;
import com.thuanhq.ticket_master.entity.Role;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    @Mapping(target = "permissions", ignore = true)
    Role toRole(RoleRequest request);

    RoleResponse toRoleResponse(Role role);

    @Mapping(target = "permissions", ignore = true)
    void toRoleFromRoleRequest(@MappingTarget Role role, RoleRequest request);
}
