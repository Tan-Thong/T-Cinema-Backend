package com.example.backend.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoomRequest {
    @NotBlank(message = "Tên phòng không được bỏ trống!")
    String roomName;
    int cinemaId;
    @NotNull(message = "Số hàng ghế không được bỏ trống!")
    @Max(value = 10, message = "Số hàng ghế tối đa là 10!")
    @Min(value = 5, message = "Số hàng ghế tối thiểu là 5!")
    int row;
    @NotNull(message = "Số cột ghế không được bỏ trống!")
    @Max(value = 12, message = "Số cột ghế tối đa là 12!")
    @Min(value = 6, message = "Số cột ghế tối thiểu là 6!")
    int column;
    String roomType;
}
