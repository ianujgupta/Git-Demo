package com.example.training.repository;

import com.example.training.entities.Role;

import java.util.Optional;

public interface RoleRepository {
    Optional<Role> findByName(String name);

}
