package com.example.backend.controller;


import com.example.backend.dto.request.UserCreationRequest;
import com.example.backend.dto.response.ApiResponse;
import com.example.backend.dto.response.UserResponse;
import com.example.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/users")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public ApiResponse<List<UserResponse>> getUsers() {
        ApiResponse<List<UserResponse>> apiResponse = new ApiResponse<>();
        apiResponse.setResult(userService.getUsers());
        return apiResponse;
    }

    @GetMapping("/users/{id}")
    public ApiResponse<UserResponse> getUser(@PathVariable("id") int UserId) {
        ApiResponse<UserResponse> apiResponse = new ApiResponse<>();
        apiResponse.setResult(userService.getUser(UserId));
        return apiResponse;
    }

    @PostMapping("/users")
    public ApiResponse<UserResponse> createUser(@RequestBody UserCreationRequest request) {
        ApiResponse<UserResponse> apiResponse = new ApiResponse<>();
        apiResponse.setResult(userService.createUser(request));
        return apiResponse;
    }

    @PutMapping("/users/{id}")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public ApiResponse<UserResponse> createUser(@PathVariable("id") int userId, @RequestBody UserCreationRequest request) {
        ApiResponse<UserResponse> apiResponse = new ApiResponse<>();
        apiResponse.setResult(userService.updateUser(userId, request));
        return apiResponse;
    }
}
