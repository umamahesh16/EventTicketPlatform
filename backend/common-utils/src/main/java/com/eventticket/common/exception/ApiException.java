package com.eventticket.common.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.Map;

@Getter
public class ApiException extends RuntimeException {
    
    private final HttpStatus status;
    private final String errorCode;
    private final LocalDateTime timestamp;
    private final Map<String, Object> details;
    
    public ApiException(String message, HttpStatus status) {
        this(message, status, null, null);
    }
    
    public ApiException(String message, HttpStatus status, String errorCode) {
        this(message, status, errorCode, null);
    }
    
    public ApiException(String message, HttpStatus status, String errorCode, Map<String, Object> details) {
        super(message);
        this.status = status;
        this.errorCode = errorCode;
        this.timestamp = LocalDateTime.now();
        this.details = details;
    }
    
    public static ApiException notFound(String message) {
        return new ApiException(message, HttpStatus.NOT_FOUND, "NOT_FOUND");
    }
    
    public static ApiException badRequest(String message) {
        return new ApiException(message, HttpStatus.BAD_REQUEST, "BAD_REQUEST");
    }
    
    public static ApiException unauthorized(String message) {
        return new ApiException(message, HttpStatus.UNAUTHORIZED, "UNAUTHORIZED");
    }
    
    public static ApiException forbidden(String message) {
        return new ApiException(message, HttpStatus.FORBIDDEN, "FORBIDDEN");
    }
    
    public static ApiException conflict(String message) {
        return new ApiException(message, HttpStatus.CONFLICT, "CONFLICT");
    }
    
    public static ApiException internalServerError(String message) {
        return new ApiException(message, HttpStatus.INTERNAL_SERVER_ERROR, "INTERNAL_SERVER_ERROR");
    }
} 