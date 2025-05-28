package com.example.backend.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CinemaRequest {
    @NotBlank(message = "Tên rạp không được bỏ trống!")
    String cinemaName;
    String city;
    @NotBlank(message = "Địa chỉ không được bỏ trống!")
    String location;
}
