package com.thuanhq.ticket_master.dto.response;

import java.time.LocalDateTime;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.thuanhq.ticket_master.entity.Permission;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class RoleResponse {
    String id;
    String name;
    String description;
    Set<Permission> permissions;
    String createdBy;

    @JsonFormat(pattern = "dd/MM/yyyy")
    LocalDateTime createdAt;

    String updatedBy;

    @JsonFormat(pattern = "dd/MM/yyyy")
    LocalDateTime updatedAt;
}
