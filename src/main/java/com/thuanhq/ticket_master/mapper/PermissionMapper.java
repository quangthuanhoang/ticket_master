package com.thuanhq.ticket_master.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import com.thuanhq.ticket_master.dto.request.PermissionRequest;
import com.thuanhq.ticket_master.dto.response.PermissionResponse;
import com.thuanhq.ticket_master.entity.Permission;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
    Permission toPermission(PermissionRequest permissionRequest);

    PermissionResponse toPermissionResponse(Permission permission);

    void toPermissionFromPermissionRequest(@MappingTarget Permission permission, PermissionRequest permissionRequest);
}
