package com.example.backend.mapper;

import com.example.backend.dto.request.UserCreationRequest;
import com.example.backend.dto.response.UserResponse;
import com.example.backend.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserCreationRequest request);
    List<UserResponse> toUsersResponse(List<User> users);
    UserResponse toUserResponse(User user);
    void updateUser(@MappingTarget User user, UserCreationRequest request);
}
