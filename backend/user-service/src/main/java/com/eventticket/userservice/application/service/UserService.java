package com.eventticket.userservice.application.service;

import com.eventticket.userservice.application.dto.UserRegistrationRequest;
import com.eventticket.userservice.application.dto.UserResponse;
import com.eventticket.userservice.application.dto.UserUpdateRequest;

import java.util.List;
import java.util.UUID;

public interface UserService {
    
    UserResponse registerUser(UserRegistrationRequest request);
    
    UserResponse getUserById(UUID userId);
    
    UserResponse getUserByEmail(String email);
    
    UserResponse getUserByUsername(String username);
    
    List<UserResponse> getAllUsers(int page, int size);
    
    UserResponse updateUser(UUID userId, UserUpdateRequest request);
    
    void deleteUser(UUID userId);
    
    void activateUser(UUID userId);
    
    void deactivateUser(UUID userId);
    
    void verifyUser(UUID userId);
    
    void assignRole(UUID userId, String roleName);
    
    void removeRole(UUID userId, String roleName);
    
    void updateLastLogin(UUID userId);
} 