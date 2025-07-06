package com.eventticket.userservice.infrastructure.repository;

import com.eventticket.userservice.domain.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, UUID> {
    
    Optional<UserRole> findByRoleName(String roleName);
    
    boolean existsByRoleName(String roleName);
} 