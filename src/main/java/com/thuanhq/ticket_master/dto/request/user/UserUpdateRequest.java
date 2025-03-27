package com.thuanhq.ticket_master.dto.request.user;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import jakarta.validation.constraints.Size;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserUpdateRequest {
    @Size(min = 8, message = "PASSWORD_INVALID")
    String password;

    String firstName;
    String lastName;
    String email;
    LocalDate dob;

    List<String> roles;
}
