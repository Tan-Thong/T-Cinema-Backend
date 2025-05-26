package com.example.backend.dto.response;

import com.example.backend.entity.Seat;
import com.example.backend.entity.Showtime;
import com.example.backend.enums.SeatStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SeatShowtimeResponse {
    Seat seat;
    Showtime showtime;
    SeatStatus seatStatus;
    int seatColumn;
    int seatRow;
    int roomId;
}
