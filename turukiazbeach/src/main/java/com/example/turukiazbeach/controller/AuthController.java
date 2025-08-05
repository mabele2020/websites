package com.example.turukiazbeach.controller;

import com.example.turukiazbeach.model.User;
import com.example.turukiazbeach.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AuthController {

    private final UserService userService;

    // Signup (default role = USER if none provided)
    @PostMapping("/signup")
    public ResponseEntity<User> signup(@RequestBody User user) {
        if (user.getEmail() == null || user.getPassword() == null) {
            return ResponseEntity.badRequest().build();
        }

        if (userService.existsByEmail(user.getEmail())) {
            return ResponseEntity.status(409).build(); // Conflict: email exists
        }
        // Set default role to USER if role is null or empty
        if (user.getRole() == null || user.getRole().isBlank()) {
            user.setRole("USER");
        }
        User createdUser = userService.createUser(user);
        return ResponseEntity.ok(createdUser);
    }

    // Login endpoint to authenticate and return user info (including role)
    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody User loginRequest) {
        if (loginRequest.getEmail() == null || loginRequest.getPassword() == null) {
            return ResponseEntity.badRequest().build();
        }

        User user = userService.findByEmail(loginRequest.getEmail());
        if (user == null) {
            return ResponseEntity.status(401).build(); // Unauthorized
        }
        // Simple password check (consider hashing in real apps)
        if (user.getPassword().equals(loginRequest.getPassword())) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(401).build();
        }
    }

    // Get all users
    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    // Get user by id
    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        if (user != null) {
            return ResponseEntity.ok(user);
        }
        return ResponseEntity.notFound().build();
    }

    // Update user by id
    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User userDetails) {
        User updatedUser = userService.updateUser(id, userDetails);
        if (updatedUser != null) {
            return ResponseEntity.ok(updatedUser);
        }
        return ResponseEntity.notFound().build();
    }

    // Delete user by id
    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        boolean deleted = userService.deleteUser(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
