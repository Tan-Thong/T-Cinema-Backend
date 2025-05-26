package com.example.backend.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TicketResponse {
    int ticketId;
    Double ticketPrice;
    String movieName;
    String time;
    String cinemaName;
    String roomName;
    String seatRow;
    int seatColumn;
}
