package com.thuanhq.ticket_master.dto.response.user;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserResponse {
    String id;
    String username;
    String firstName;
    String lastName;
    LocalDate dob;
    String createdBy;

    @JsonFormat(pattern = "dd/MM/yyyy")
    LocalDateTime createdAt;

    String updatedBy;

    @JsonFormat(pattern = "dd/MM/yyyy")
    LocalDateTime updatedAt;
}
