package com.thuanhq.ticket_master.service;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.thuanhq.ticket_master.dto.request.RoleRequest;
import com.thuanhq.ticket_master.dto.response.RoleResponse;
import com.thuanhq.ticket_master.entity.Permission;
import com.thuanhq.ticket_master.entity.Role;
import com.thuanhq.ticket_master.exception.ApplicationException;
import com.thuanhq.ticket_master.exception.ErrorCode;
import com.thuanhq.ticket_master.mapper.RoleMapper;
import com.thuanhq.ticket_master.repository.PermissionRepository;
import com.thuanhq.ticket_master.repository.RoleRepository;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@Service
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Slf4j
public class RoleService {
    RoleRepository roleRepository;
    RoleMapper roleMapper;
    PermissionRepository permissionRepository;

    public RoleResponse create(RoleRequest roleRequest) {
        Role role = roleMapper.toRole(roleRequest);

        List<Permission> permissions = permissionRepository.findAllById(roleRequest.getPermissions());
        role.setPermissions(new HashSet<>(permissions));

        return roleMapper.toRoleResponse(roleRepository.save(role));
    }

    public List<RoleResponse> findAll() {
        var roles = roleRepository.findAll();
        return roles.stream().map(roleMapper::toRoleResponse).collect(Collectors.toList());
    }

    public String update(String roleId, RoleRequest roleRequest) {
        Role role = roleRepository
                .findById(roleId)
                .orElseThrow(() -> new ApplicationException(ErrorCode.PERMISSION_NOT_EXISTED));

        List<Permission> permissions = permissionRepository.findAllById(roleRequest.getPermissions());
        role.setPermissions(new HashSet<>(permissions));

        roleMapper.toRoleFromRoleRequest(role, roleRequest);
        roleRepository.save(role);

        return role.getId();
    }

    public void delete(String roleId) {
        Role role = roleRepository
                .findById(roleId)
                .orElseThrow(() -> new ApplicationException(ErrorCode.PERMISSION_NOT_EXISTED));

        roleRepository.delete(role);
    }
}
