package com.example.spiringsecurity.service;

import com.example.spiringsecurity.exception.UserExceptions;
import com.example.spiringsecurity.model.User;
import com.example.spiringsecurity.payload.request.RegisterRequest;
import com.example.spiringsecurity.payload.request.UpdateRequest;
import com.example.spiringsecurity.payload.response.ApiResponse;
import com.example.spiringsecurity.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final AuthenticationService authenticationService;

    /**
     * Get All user
     */
    public List<User> getAll() {
        return userRepository.findAll();
    }

    /**
     * Save or update user to db.
     */
    public ApiResponse save(RegisterRequest request) {

        return authenticationService.register(request);
    }
    /**
     * Save or update user to db.
     */
    public User update(UpdateRequest request) {

        User user = userRepository.findById(request.getId());

        if(user == null){
            throw new UserExceptions(HttpStatus.NOT_FOUND, "User not found");
        }

        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setDesignation(request.getDesignation());
        user.setDeptMstCode(request.getDeptMstCode());
        user.setRole(request.getRole());

        return userRepository.save(user);
    }


    /**
     * Find a user by id.
     */
    public User getById(Long id) {
        return userRepository.findById(id);
    }

    /**
     * Delete a user by id.
     */
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    public Page<User> searchUsers(String name, String designation, String deptMstCode, Pageable pageable) {
        return userRepository.findByNameContainingAndDesignationContainingAndDeptMstCodeContaining(
                name, designation, deptMstCode, pageable);
    }
}
