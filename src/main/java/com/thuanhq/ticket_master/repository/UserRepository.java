package com.thuanhq.ticket_master.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.thuanhq.ticket_master.entity.User;

public interface UserRepository extends JpaRepository<User, String> {
    boolean existsByUsername(String username);

    Optional<User> findByUsername(String username);
}
