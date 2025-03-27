package com.thuanhq.ticket_master.entity;

import java.time.LocalDate;
import java.util.Set;

import jakarta.persistence.Entity;

import jakarta.persistence.ManyToMany;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Data
@Builder
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

    @ManyToMany
    Set<Role> roles;

    public void setPassword(String rawPassword) {
        this.password = ENCODER.encode(rawPassword);
    }
}
