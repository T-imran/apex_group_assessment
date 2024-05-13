package com.example.spiringsecurity.controller;

import com.example.spiringsecurity.model.User;
import com.example.spiringsecurity.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Collections;
import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    /**
     * Get All created projects
     */
    @GetMapping("/getAll")
    public ResponseEntity<?> getAll() {
        try {
            List<User> projects = userService.getAll();
            return ResponseEntity.ok(projects);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Collections.singletonMap("Error", e.getMessage()));
        }
    }

    /**
     * Save a project.
     */
    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody User task) {
        try {
            return ResponseEntity.ok(userService.save(task));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Collections.singletonMap("Error", e.getMessage()));
        }
    }

    /**
     * Get an action by ID.
     */
    @GetMapping("/get-by-id")
    public ResponseEntity<?> getById(@RequestParam Long id) {
        try {
            return ResponseEntity.ok(userService.getById(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Collections.singletonMap("Error", e.getMessage()));
        }
    }

//    @GetMapping("/get-by-status")
//    public ResponseEntity<?> getByStatus(@RequestParam String status) {
//        try {
//            List<User> getByStatus = userService.getByStatusService(status);
//            return ResponseEntity.ok(getByStatus);
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().body(Collections.singletonMap("Error", e.getMessage()));
//        }
//    }

    /**
     * Delete a post by ID.
     */
    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteById(@RequestParam Long id) throws IOException {
        try {
            userService.deleteUserById(id);
            return ResponseEntity.ok(Collections.singletonMap("Message", "Project deleted successfully"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Collections.singletonMap("Error", e.getMessage()));
        }
    }
}
