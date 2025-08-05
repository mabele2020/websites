package com.example.turukiazbeach.service;

import com.example.turukiazbeach.model.User;

import java.util.List;

public interface UserService {
    User createUser(User user);
    List<User> getAllUsers();
    User getUserById(Long id);
    User updateUser(Long id, User user);
    boolean deleteUser(Long id);
    boolean existsByEmail(String email);

    // Add this method declaration if it's missing
    User getUserByEmail(String email);

    User findByEmail(String email);
}
