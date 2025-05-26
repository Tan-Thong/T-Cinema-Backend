package com.example.backend.mapper;

import com.example.backend.dto.response.BookingResponse;
import com.example.backend.entity.Booking;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {TicketMapper.class, UserMapper.class})
public interface BookingMapper {
    @Mapping(source = "tickets", target = "ticketsResponse")
    BookingResponse toBookingResponse(Booking booking);
    List<BookingResponse> toBookingsResponse(List<Booking> bookings);
}
