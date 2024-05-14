package com.example.spiringsecurity.repository;

import com.example.spiringsecurity.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {

    Optional<User> findByEmail(String email);

    User findById(Long id);
    Optional<User> deleteById(Long id);

    Page<User> findByNameContainingAndDesignationContainingAndDeptMstCodeContaining(String name, String designation, String deptMstCode, Pageable pageable);
}
