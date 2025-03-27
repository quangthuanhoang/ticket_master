package com.thuanhq.ticket_master.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Permission extends AbstractEntity<String> {
    @Column(unique = true, nullable = false)
    String name;

    String description;
}
