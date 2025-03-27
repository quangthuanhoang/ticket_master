package com.thuanhq.ticket_master.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.thuanhq.ticket_master.dto.request.RoleRequest;
import com.thuanhq.ticket_master.dto.response.APIResponse;
import com.thuanhq.ticket_master.dto.response.RoleResponse;
import com.thuanhq.ticket_master.dto.response.RoleUpdateResponse;
import com.thuanhq.ticket_master.service.RoleService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@RequestMapping("/roles")
public class RoleController {
    RoleService roleService;

    @PostMapping
    public APIResponse<RoleResponse> create(@RequestBody RoleRequest roleRequest) {

        return APIResponse.<RoleResponse>builder()
                .code(HttpStatus.CREATED.value())
                .result(roleService.create(roleRequest))
                .build();
    }

    @GetMapping
    public APIResponse<List<RoleResponse>> getAll() {
        return APIResponse.<List<RoleResponse>>builder()
                .result(roleService.findAll())
                .build();
    }

    @PutMapping("/{roleId}")
    public APIResponse<RoleUpdateResponse> update(@PathVariable String roleId, @RequestBody RoleRequest roleRequest) {
        return APIResponse.<RoleUpdateResponse>builder()
                .result(RoleUpdateResponse.builder()
                        .id(roleService.update(roleId, roleRequest))
                        .build())
                .build();
    }

    @DeleteMapping("/{roleId}")
    public APIResponse<Boolean> delete(@PathVariable String roleId) {
        roleService.delete(roleId);
        return APIResponse.<Boolean>builder().result(true).build();
    }
}
