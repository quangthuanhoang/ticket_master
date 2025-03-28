package com.thuanhq.ticket_master.dto.request.user;

import java.time.LocalDate;

import com.thuanhq.ticket_master.validator.dob.DobConstraint;
import jakarta.validation.constraints.Size;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserCreationRequest {

    @Size(min = 3, message = "USERNAME_INVALID")
    String username;

    @Size(min = 8, message = "PASSWORD_INVALID")
    String password;

    String firstName;
    String lastName;
    String email;
    @DobConstraint(min = 5, message = "INVALID_DOB")
    LocalDate dob;
}
