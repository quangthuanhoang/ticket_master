package com.thuanhq.ticket_master.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.thuanhq.ticket_master.entity.Role;

public interface RoleRepository extends JpaRepository<Role, String> {}
