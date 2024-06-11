package com.example.demo.repository;

import com.example.demo.model.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<UserRole, Integer> {
    Optional<UserRole> findByName(String roleName);
}
