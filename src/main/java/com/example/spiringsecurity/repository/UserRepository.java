package com.example.spiringsecurity.repository;

import com.example.spiringsecurity.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {

    Optional<User> findByEmail(String email);

    Optional<User> findById(Long id);
    Optional<User> deleteById(Long id);
}
