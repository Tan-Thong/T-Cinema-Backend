package com.example.backend.service;

import com.example.backend.dto.request.UserCreationRequest;
import com.example.backend.dto.response.BookingResponse;
import com.example.backend.dto.response.UserResponse;
import com.example.backend.entity.User;
import com.example.backend.enums.Role;
import com.example.backend.mapper.BookingMapper;
import com.example.backend.mapper.UserMapper;
import com.example.backend.repository.BookingRepository;
import com.example.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private BookingMapper bookingMapper;

    @Autowired
    private BookingRepository bookingRepository;

    public UserResponse createUser (UserCreationRequest request) {
        User user = userMapper.toUser(request);
        if(userRepository.existsByEmail(user.getEmail()))
            throw new RuntimeException("User đã tồn tại!");
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        Set<String> roles = new HashSet<>();
        roles.add(Role.USER.name());
        user.setRoles(roles);
        return userMapper.toUserResponse(userRepository.save(user));
    }

    public UserResponse updateUser (int userId, UserCreationRequest request) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User không tồn tại!"));
        userMapper.updateUser(user, request);
        return userMapper.toUserResponse(userRepository.save(user));
    }

    public UserResponse getMyInfo() {
        var context  = SecurityContextHolder.getContext();
        String email = context.getAuthentication().getName();
        User user = userRepository.findByEmail(email).orElseThrow(() ->new RuntimeException("Username không tồn tại!"));
        return userMapper.toUserResponse(user);
    }

    public UserResponse getUser(int userId) {
        return userMapper.toUserResponse(userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User không tồn tại!")));
    }

    public void deleteUser(int userId) {
        User user = userRepository.findById(userId).orElseThrow(()->new RuntimeException("User không tồn tại"));
        userRepository.delete(user);
    }

    public List<UserResponse> getUsers() {
        return userMapper.toUsersResponse(userRepository.findAll());
    }

    public List<BookingResponse> getBookingsByUserId(int userId) {
        return bookingMapper.toBookingsResponse(bookingRepository.findByUser_UserId(userId));
    }
}
