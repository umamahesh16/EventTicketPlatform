package com.eventticket.userservice.infrastructure.service;

import com.eventticket.common.exception.ApiException;
import com.eventticket.userservice.application.dto.UserRegistrationRequest;
import com.eventticket.userservice.application.dto.UserResponse;
import com.eventticket.userservice.application.dto.UserUpdateRequest;
import com.eventticket.userservice.application.service.UserService;
import com.eventticket.userservice.domain.entity.User;
import com.eventticket.userservice.domain.entity.UserRole;
import com.eventticket.userservice.infrastructure.mapper.UserMapper;
import com.eventticket.userservice.infrastructure.repository.UserRepository;
import com.eventticket.userservice.infrastructure.repository.UserRoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class UserServiceImpl implements UserService {
    
    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    
    @Override
    public UserResponse registerUser(UserRegistrationRequest request) {
        log.info("Registering new user with email: {}", request.getEmail());
        
        // Check if user already exists
        if (userRepository.existsByEmail(request.getEmail())) {
            throw ApiException.conflict("User with email " + request.getEmail() + " already exists");
        }
        
        if (userRepository.existsByUsername(request.getUsername())) {
            throw ApiException.conflict("Username " + request.getUsername() + " is already taken");
        }
        
        // Create new user
        User user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .passwordHash(passwordEncoder.encode(request.getPassword()))
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .phone(request.getPhone())
                .dateOfBirth(request.getDateOfBirth())
                .isActive(true)
                .isVerified(false)
                .build();
        
        // Assign default role (ATTENDEE)
        UserRole attendeeRole = userRoleRepository.findByRoleName("ATTENDEE")
                .orElseThrow(() -> ApiException.internalServerError("Default role ATTENDEE not found"));
        user.addRole(attendeeRole);
        
        User savedUser = userRepository.save(user);
        log.info("User registered successfully with ID: {}", savedUser.getUserId());
        
        return userMapper.toUserResponse(savedUser);
    }
    
    @Override
    @Transactional(readOnly = true)
    public UserResponse getUserById(UUID userId) {
        log.info("Fetching user by ID: {}", userId);
        
        User user = userRepository.findById(userId)
                .orElseThrow(() -> ApiException.notFound("User not found with ID: " + userId));
        
        return userMapper.toUserResponse(user);
    }
    
    @Override
    @Transactional(readOnly = true)
    public UserResponse getUserByEmail(String email) {
        log.info("Fetching user by email: {}", email);
        
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> ApiException.notFound("User not found with email: " + email));
        
        return userMapper.toUserResponse(user);
    }
    
    @Override
    @Transactional(readOnly = true)
    public UserResponse getUserByUsername(String username) {
        log.info("Fetching user by username: {}", username);
        
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> ApiException.notFound("User not found with username: " + username));
        
        return userMapper.toUserResponse(user);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<UserResponse> getAllUsers(int page, int size) {
        log.info("Fetching users with page: {}, size: {}", page, size);
        
        Page<User> userPage = userRepository.findAll(PageRequest.of(page, size));
        return userPage.getContent().stream()
                .map(userMapper::toUserResponse)
                .collect(Collectors.toList());
    }
    
    @Override
    public UserResponse updateUser(UUID userId, UserUpdateRequest request) {
        log.info("Updating user with ID: {}", userId);
        
        User user = userRepository.findById(userId)
                .orElseThrow(() -> ApiException.notFound("User not found with ID: " + userId));
        
        // Update fields if provided
        if (request.getFirstName() != null) {
            user.setFirstName(request.getFirstName());
        }
        if (request.getLastName() != null) {
            user.setLastName(request.getLastName());
        }
        if (request.getPhone() != null) {
            user.setPhone(request.getPhone());
        }
        if (request.getDateOfBirth() != null) {
            user.setDateOfBirth(request.getDateOfBirth());
        }
        
        User updatedUser = userRepository.save(user);
        log.info("User updated successfully with ID: {}", updatedUser.getUserId());
        
        return userMapper.toUserResponse(updatedUser);
    }
    
    @Override
    public void deleteUser(UUID userId) {
        log.info("Deleting user with ID: {}", userId);
        
        if (!userRepository.existsById(userId)) {
            throw ApiException.notFound("User not found with ID: " + userId);
        }
        
        userRepository.deleteById(userId);
        log.info("User deleted successfully with ID: {}", userId);
    }
    
    @Override
    public void activateUser(UUID userId) {
        log.info("Activating user with ID: {}", userId);
        
        User user = userRepository.findById(userId)
                .orElseThrow(() -> ApiException.notFound("User not found with ID: " + userId));
        
        user.activate();
        userRepository.save(user);
        log.info("User activated successfully with ID: {}", userId);
    }
    
    @Override
    public void deactivateUser(UUID userId) {
        log.info("Deactivating user with ID: {}", userId);
        
        User user = userRepository.findById(userId)
                .orElseThrow(() -> ApiException.notFound("User not found with ID: " + userId));
        
        user.deactivate();
        userRepository.save(user);
        log.info("User deactivated successfully with ID: {}", userId);
    }
    
    @Override
    public void verifyUser(UUID userId) {
        log.info("Verifying user with ID: {}", userId);
        
        User user = userRepository.findById(userId)
                .orElseThrow(() -> ApiException.notFound("User not found with ID: " + userId));
        
        user.markAsVerified();
        userRepository.save(user);
        log.info("User verified successfully with ID: {}", userId);
    }
    
    @Override
    public void assignRole(UUID userId, String roleName) {
        log.info("Assigning role {} to user with ID: {}", roleName, userId);
        
        User user = userRepository.findById(userId)
                .orElseThrow(() -> ApiException.notFound("User not found with ID: " + userId));
        
        UserRole role = userRoleRepository.findByRoleName(roleName)
                .orElseThrow(() -> ApiException.notFound("Role not found: " + roleName));
        
        user.addRole(role);
        userRepository.save(user);
        log.info("Role {} assigned successfully to user with ID: {}", roleName, userId);
    }
    
    @Override
    public void removeRole(UUID userId, String roleName) {
        log.info("Removing role {} from user with ID: {}", roleName, userId);
        
        User user = userRepository.findById(userId)
                .orElseThrow(() -> ApiException.notFound("User not found with ID: " + userId));
        
        UserRole role = userRoleRepository.findByRoleName(roleName)
                .orElseThrow(() -> ApiException.notFound("Role not found: " + roleName));
        
        user.removeRole(role);
        userRepository.save(user);
        log.info("Role {} removed successfully from user with ID: {}", roleName, userId);
    }
    
    @Override
    public void updateLastLogin(UUID userId) {
        log.debug("Updating last login for user with ID: {}", userId);
        
        User user = userRepository.findById(userId)
                .orElseThrow(() -> ApiException.notFound("User not found with ID: " + userId));
        
        user.updateLastLogin();
        userRepository.save(user);
    }
} 