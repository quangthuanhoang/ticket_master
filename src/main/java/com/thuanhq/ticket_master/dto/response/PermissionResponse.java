package com.thuanhq.ticket_master.dto.response;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class PermissionResponse {
    String id;
    String name;
    String description;
    String createdBy;

    @JsonFormat(pattern = "dd/MM/yyyy")
    LocalDateTime createdAt;

    String updatedBy;

    @JsonFormat(pattern = "dd/MM/yyyy")
    LocalDateTime updatedAt;
}
