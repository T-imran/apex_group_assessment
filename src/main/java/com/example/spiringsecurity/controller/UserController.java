package com.example.spiringsecurity.controller;

import com.example.spiringsecurity.exception.UserExceptions;
import com.example.spiringsecurity.model.User;
import com.example.spiringsecurity.payload.request.RegisterRequest;
import com.example.spiringsecurity.payload.request.UpdateRequest;
import com.example.spiringsecurity.payload.response.ApiResponse;
import com.example.spiringsecurity.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
@Slf4j
public class UserController {

    private final UserService userService;

    /**
     * Get All user
     */
    @GetMapping("/getAll")
    public ResponseEntity<ApiResponse> getAll() {
        try {
            List<User> projects = userService.getAll();
            ApiResponse apiResponse = new ApiResponse(true,"Get all user",projects,null);
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error while get all user: "+ e.getMessage());
            throw new UserExceptions(HttpStatus.INTERNAL_SERVER_ERROR,"Error while getting users");
        }
    }

    /**
     * Save or update a user.
     */
    @PostMapping("/save")
    public ResponseEntity<ApiResponse> save(@RequestBody RegisterRequest request) {
        try {
            ApiResponse apiResponse = userService.save(request);
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error while save or update user: "+ e.getMessage());
            throw new UserExceptions(HttpStatus.INTERNAL_SERVER_ERROR,"Error while getting users");
        }
    }

    /**
     * Save or update a user.
     */
    @PutMapping("/update")
    public ResponseEntity<ApiResponse> save(@RequestBody UpdateRequest request) {
        try {
            User user = userService.update(request);

            ApiResponse apiResponse = new ApiResponse(true,"User updated successfully",user,null);
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error while save or update user: "+ e.getMessage());
            throw new UserExceptions(HttpStatus.INTERNAL_SERVER_ERROR,"Error while getting users");
        }
    }

    /**
     * Get a user by ID.
     */
    @GetMapping("/get-by-id")
    public ResponseEntity<ApiResponse> getById(@RequestParam Long id) {
        try {
            User user = userService.getById(id);
            ApiResponse apiResponse = new ApiResponse(true,"Get user by id",user,null);
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error while getting user: "+ e.getMessage());
            throw new UserExceptions(HttpStatus.INTERNAL_SERVER_ERROR,"Error while getting users");
        }
    }

    @GetMapping("/search")
    public ResponseEntity<Page<User>> searchUsers(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String designation,
            @RequestParam(required = false) String deptMstCode,
            Pageable pageable) {
        Page<User> users = userService.searchUsers(name, designation, deptMstCode, pageable);
        return ResponseEntity.ok(users);

    }

    /**
     * Delete a user by ID.
     */
    @DeleteMapping("/delete")
    @Transactional
    public ResponseEntity<ApiResponse> deleteById(@RequestParam Long id) {
        try {
            userService.deleteUserById(id);
            ApiResponse apiResponse = new ApiResponse(true,"User deleted successfully",null,null);
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error while deleting user: "+ e.getMessage());
            throw new UserExceptions(HttpStatus.INTERNAL_SERVER_ERROR,"Error while getting users");
        }
    }
}
