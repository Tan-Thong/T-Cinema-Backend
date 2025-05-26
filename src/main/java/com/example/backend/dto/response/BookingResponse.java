package com.example.backend.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.sql.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookingResponse {
    int bookingId;
    UserResponse user;
    Date bookingDate;
    Double totalPrice;
    List<TicketResponse> ticketsResponse;
    String paymentName;
}
