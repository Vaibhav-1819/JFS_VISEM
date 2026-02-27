package com.example.snb2user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.snb2user.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}