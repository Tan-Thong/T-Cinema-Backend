package com.example.backend.mapper;

import com.example.backend.dto.response.TicketResponse;
import com.example.backend.entity.Ticket;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TicketMapper {
    @Mapping(source = "showtime.showTime", target = "time")
    @Mapping(source = "showtime.movie.title", target = "movieName")
    @Mapping(source = "showtime.room.roomName", target = "roomName")
    @Mapping(source = "showtime.room.cinema.cinemaName", target = "cinemaName")
    @Mapping(source = "seat.seatRow", target = "seatRow")
    @Mapping(source = "seat.seatColumn", target = "seatColumn")
    TicketResponse toTicketResponse(Ticket ticket);
    List<TicketResponse> toTicketsesponse(List<Ticket> tickets);
}
