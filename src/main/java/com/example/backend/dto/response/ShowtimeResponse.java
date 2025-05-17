package com.example.backend.dto.response;

import com.example.backend.entity.Movie;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ShowtimeResponse {
    int showtimeId;
    LocalDate showDate;
    String showTime;
    Movie movie;
    RoomResponse room;
}
