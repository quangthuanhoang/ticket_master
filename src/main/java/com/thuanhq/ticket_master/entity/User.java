package com.thuanhq.ticket_master.entity;

import java.time.LocalDate;
import java.util.Set;

import com.thuanhq.ticket_master.validator.dob.DobConstraint;
import jakarta.persistence.Entity;

import jakarta.persistence.ManyToMany;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User extends AbstractEntity<String> {
    String username;
    String password;
    String firstName;
    String lastName;
    String email;
    LocalDate dob;

    @ManyToMany
    Set<Role> roles;

}
