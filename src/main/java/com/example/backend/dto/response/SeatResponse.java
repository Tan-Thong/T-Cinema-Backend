package com.example.backend.dto.response;

import com.example.backend.enums.SeatType;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SeatResponse {
    int seatId;
    char seatRow;
    int seatColumn;
    SeatType seatType;
    RoomResponse room;
}
