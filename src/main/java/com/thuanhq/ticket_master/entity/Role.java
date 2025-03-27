package com.thuanhq.ticket_master.entity;

import java.util.Set;

import jakarta.persistence.*;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Getter
@Setter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Role extends AbstractEntity<String> {
    @Column(unique = true, nullable = false)
    String name;

    String description;

    @ManyToMany
    Set<Permission> permissions;
}
