package com.example.spiringsecurity.service;

import com.example.spiringsecurity.model.User;
import com.example.spiringsecurity.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    /**
     * Get All user
     */
    public List<User> getAll() {
        return userRepository.findAll();
    }

    /**
     * Save or update user to db.
     */
    public User save(User project) {
        return userRepository.save(project);
    }

    /**
     * Find a user by id.
     */
    public User getById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    /**
     * Delete a user by id.
     */
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

//    public List<User> getByStatusService(String status){
//        return userRepository.findByStatusContaining(status);
//    }
}
