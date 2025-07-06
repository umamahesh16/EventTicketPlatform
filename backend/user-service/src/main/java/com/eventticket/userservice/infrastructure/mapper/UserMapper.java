package com.eventticket.userservice.infrastructure.mapper;

import com.eventticket.userservice.application.dto.UserResponse;
import com.eventticket.userservice.domain.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface UserMapper {
    
    @Mapping(target = "roles", expression = "java(mapRoles(user.getRoles()))")
    UserResponse toUserResponse(User user);
    
    default Set<String> mapRoles(Set<com.eventticket.userservice.domain.entity.UserRole> roles) {
        if (roles == null) {
            return null;
        }
        return roles.stream()
                .map(com.eventticket.userservice.domain.entity.UserRole::getRoleName)
                .collect(Collectors.toSet());
    }
} 