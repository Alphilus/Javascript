package com.example.demo.repository;

import com.example.demo.entity.User;
import com.example.demo.model.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findUserById(Integer id);
    List<User> findByRole(UserRole role);

    Optional<User> findByEmail(String email);

    User findUserByIdAndEmail(Integer id, String email);
}
