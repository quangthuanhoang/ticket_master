package com.thuanhq.ticket_master.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User extends AbstractEntity<String> {

    String username;
    String password;
    String firstName;
    String lastName;
    LocalDate dob;
}
