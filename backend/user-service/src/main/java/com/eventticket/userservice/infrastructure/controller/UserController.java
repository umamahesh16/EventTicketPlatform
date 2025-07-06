package com.eventticket.userservice.infrastructure.controller;

import com.eventticket.common.response.ApiResponse;
import com.eventticket.userservice.application.dto.UserRegistrationRequest;
import com.eventticket.userservice.application.dto.UserResponse;
import com.eventticket.userservice.application.dto.UserUpdateRequest;
import com.eventticket.userservice.application.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "User Management", description = "APIs for user management and registration")
public class UserController {
    
    private final UserService userService;
    
    @PostMapping("/register")
    @Operation(summary = "Register a new user", description = "Creates a new user account with default ATTENDEE role")
    public ResponseEntity<ApiResponse<UserResponse>> registerUser(@Valid @RequestBody UserRegistrationRequest request) {
        log.info("Received user registration request for email: {}", request.getEmail());
        
        UserResponse userResponse = userService.registerUser(request);
        
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(userResponse, "User registered successfully"));
    }
    
    @GetMapping("/{userId}")
    @Operation(summary = "Get user by ID", description = "Retrieves user information by user ID")
    @PreAuthorize("hasRole('ADMIN') or #userId == authentication.principal.userId")
    public ResponseEntity<ApiResponse<UserResponse>> getUserById(@PathVariable UUID userId) {
        log.info("Fetching user with ID: {}", userId);
        
        UserResponse userResponse = userService.getUserById(userId);
        
        return ResponseEntity.ok(ApiResponse.success(userResponse));
    }
    
    @GetMapping("/email/{email}")
    @Operation(summary = "Get user by email", description = "Retrieves user information by email address")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<UserResponse>> getUserByEmail(@PathVariable String email) {
        log.info("Fetching user with email: {}", email);
        
        UserResponse userResponse = userService.getUserByEmail(email);
        
        return ResponseEntity.ok(ApiResponse.success(userResponse));
    }
    
    @GetMapping("/username/{username}")
    @Operation(summary = "Get user by username", description = "Retrieves user information by username")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<UserResponse>> getUserByUsername(@PathVariable String username) {
        log.info("Fetching user with username: {}", username);
        
        UserResponse userResponse = userService.getUserByUsername(username);
        
        return ResponseEntity.ok(ApiResponse.success(userResponse));
    }
    
    @GetMapping
    @Operation(summary = "Get all users", description = "Retrieves a paginated list of all users")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<List<UserResponse>>> getAllUsers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        log.info("Fetching users with page: {}, size: {}", page, size);
        
        List<UserResponse> users = userService.getAllUsers(page, size);
        
        return ResponseEntity.ok(ApiResponse.success(users));
    }
    
    @PutMapping("/{userId}")
    @Operation(summary = "Update user", description = "Updates user information")
    @PreAuthorize("hasRole('ADMIN') or #userId == authentication.principal.userId")
    public ResponseEntity<ApiResponse<UserResponse>> updateUser(
            @PathVariable UUID userId,
            @Valid @RequestBody UserUpdateRequest request) {
        log.info("Updating user with ID: {}", userId);
        
        UserResponse userResponse = userService.updateUser(userId, request);
        
        return ResponseEntity.ok(ApiResponse.success(userResponse, "User updated successfully"));
    }
    
    @DeleteMapping("/{userId}")
    @Operation(summary = "Delete user", description = "Deletes a user account")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<Void>> deleteUser(@PathVariable UUID userId) {
        log.info("Deleting user with ID: {}", userId);
        
        userService.deleteUser(userId);
        
        return ResponseEntity.ok(ApiResponse.success(null, "User deleted successfully"));
    }
    
    @PostMapping("/{userId}/activate")
    @Operation(summary = "Activate user", description = "Activates a deactivated user account")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<Void>> activateUser(@PathVariable UUID userId) {
        log.info("Activating user with ID: {}", userId);
        
        userService.activateUser(userId);
        
        return ResponseEntity.ok(ApiResponse.success(null, "User activated successfully"));
    }
    
    @PostMapping("/{userId}/deactivate")
    @Operation(summary = "Deactivate user", description = "Deactivates a user account")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<Void>> deactivateUser(@PathVariable UUID userId) {
        log.info("Deactivating user with ID: {}", userId);
        
        userService.deactivateUser(userId);
        
        return ResponseEntity.ok(ApiResponse.success(null, "User deactivated successfully"));
    }
    
    @PostMapping("/{userId}/verify")
    @Operation(summary = "Verify user", description = "Marks a user as verified")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<Void>> verifyUser(@PathVariable UUID userId) {
        log.info("Verifying user with ID: {}", userId);
        
        userService.verifyUser(userId);
        
        return ResponseEntity.ok(ApiResponse.success(null, "User verified successfully"));
    }
    
    @PostMapping("/{userId}/roles/{roleName}")
    @Operation(summary = "Assign role", description = "Assigns a role to a user")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<Void>> assignRole(
            @PathVariable UUID userId,
            @PathVariable String roleName) {
        log.info("Assigning role {} to user with ID: {}", roleName, userId);
        
        userService.assignRole(userId, roleName);
        
        return ResponseEntity.ok(ApiResponse.success(null, "Role assigned successfully"));
    }
    
    @DeleteMapping("/{userId}/roles/{roleName}")
    @Operation(summary = "Remove role", description = "Removes a role from a user")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<Void>> removeRole(
            @PathVariable UUID userId,
            @PathVariable String roleName) {
        log.info("Removing role {} from user with ID: {}", roleName, userId);
        
        userService.removeRole(userId, roleName);
        
        return ResponseEntity.ok(ApiResponse.success(null, "Role removed successfully"));
    }
} 