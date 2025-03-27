package com.thuanhq.ticket_master.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.thuanhq.ticket_master.dto.request.PermissionRequest;
import com.thuanhq.ticket_master.dto.response.PermissionResponse;
import com.thuanhq.ticket_master.entity.Permission;
import com.thuanhq.ticket_master.exception.ApplicationException;
import com.thuanhq.ticket_master.exception.ErrorCode;
import com.thuanhq.ticket_master.mapper.PermissionMapper;
import com.thuanhq.ticket_master.repository.PermissionRepository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PermissionService {
    PermissionRepository permissionRepository;
    PermissionMapper permissionMapper;

    public PermissionResponse create(PermissionRequest request) {
        Permission permission = permissionMapper.toPermission(request);
        return permissionMapper.toPermissionResponse(permissionRepository.save(permission));
    }

    public List<PermissionResponse> findAll() {
        List<Permission> permissions = permissionRepository.findAll();
        return permissions.stream()
                .map((permissionMapper::toPermissionResponse))
                .toList();
    }

    public String update(String permissionId, PermissionRequest request) {
        Permission permission = permissionRepository
                .findById(permissionId)
                .orElseThrow(() -> new ApplicationException(ErrorCode.PERMISSION_NOT_EXISTED));
        permissionMapper.toPermissionFromPermissionRequest(permission, request);

        permissionRepository.save(permission);

        return permission.getId();
    }

    public void delete(String permissionId) {
        Permission permission = permissionRepository
                .findById(permissionId)
                .orElseThrow(() -> new ApplicationException(ErrorCode.PERMISSION_NOT_EXISTED));
        permissionRepository.deleteById(permissionId);
    }
}
