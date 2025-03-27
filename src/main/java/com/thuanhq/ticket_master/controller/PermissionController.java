package com.thuanhq.ticket_master.controller;

import java.util.List;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.thuanhq.ticket_master.dto.request.PermissionRequest;
import com.thuanhq.ticket_master.dto.response.APIResponse;
import com.thuanhq.ticket_master.dto.response.PermissionResponse;
import com.thuanhq.ticket_master.dto.response.PermissionUpdateResponse;
import com.thuanhq.ticket_master.service.PermissionService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/permissions")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class PermissionController {
    PermissionService permissionService;

    @PostMapping
    public APIResponse<PermissionResponse> create(@RequestBody PermissionRequest permissionRequest) {

        return APIResponse.<PermissionResponse>builder()
                .result(permissionService.create(permissionRequest))
                .code(HttpStatus.CREATED.value())
                .build();
    }

    @GetMapping
    public APIResponse<List<PermissionResponse>> getAll() {
        return APIResponse.<List<PermissionResponse>>builder()
                .result(permissionService.findAll())
                .build();
    }

    @PutMapping("/{permissionId}")
    public APIResponse<PermissionUpdateResponse> update(
            @PathVariable String permissionId, @RequestBody @Valid PermissionRequest permissionRequest) {
        return APIResponse.<PermissionUpdateResponse>builder()
                .result(PermissionUpdateResponse.builder()
                        .id(permissionService.update(permissionId, permissionRequest))
                        .build())
                .build();
    }

    @DeleteMapping("/{permissionId}")
    public APIResponse<Boolean> delete(@PathVariable String permissionId) {
        permissionService.delete(permissionId);
        return APIResponse.<Boolean>builder().result(true).build();
    }
}
