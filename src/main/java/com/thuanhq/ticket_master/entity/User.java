package com.thuanhq.ticket_master.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

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
    static final PasswordEncoder ENCODER = new BCryptPasswordEncoder(10);
    String username;
    String password;
    String firstName;
    String lastName;
    String email;
    LocalDate dob;

    public void setPassword(String rawPassword) {
        this.password = ENCODER.encode(rawPassword);
    }
}
