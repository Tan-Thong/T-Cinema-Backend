package com.example.backend.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserCreationRequest {
    @NotBlank(message = "Mật khẩu không được bỏ trống!")
    String password;

    @Email
    @NotBlank(message = "Email không được bỏ trống!")
    String email;

    String phoneNumber;
    @NotBlank(message = "Họ tên không được bỏ trống!")
    String fullName;
    boolean active;
}
