package com.example.backend.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ShowtimeRequest {
    LocalDate showDate;
    @NotBlank(message = "Suất chiếu không được bỏ trống!")
    String showTime;
    int roomId;
    int movieId;
    int cinemaId;
}
