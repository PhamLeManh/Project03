package com.phamlemanh.lesson01_spring_boot.repository;

import com.phamlemanh.lesson01_spring_boot.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByNameContaining(String name);
    User findByEmail(String email);
    boolean existsByEmail(String email);
}