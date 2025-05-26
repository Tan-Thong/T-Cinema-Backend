package com.example.backend.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookingRequest {
    String userId;
    int showtimeId;
    List<Integer> seatIds;
    double totalPrice;
    String paymentMethod;
}
