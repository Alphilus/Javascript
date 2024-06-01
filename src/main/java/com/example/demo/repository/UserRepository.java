package com.example.demo.repository;

import com.example.demo.entity.User;
import com.example.demo.model.enums.UserRole;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findUserById(Integer id);
    List<User> findByRole(UserRole role);

    Page<User> findAllByRole(UserRole role, PageRequest pageRequest);

    Optional<User> findByEmail(String email);

    User findUserByIdAndEmail(Integer id, String email);
}
