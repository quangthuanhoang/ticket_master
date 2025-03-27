package com.thuanhq.ticket_master.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.thuanhq.ticket_master.entity.Permission;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, String> {}
