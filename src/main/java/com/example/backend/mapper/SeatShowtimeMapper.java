package com.example.backend.mapper;

import com.example.backend.dto.response.SeatResponse;
import com.example.backend.dto.response.SeatShowtimeResponse;
import com.example.backend.entity.Seat;
import com.example.backend.entity.SeatShowtime;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SeatShowtimeMapper {
    @Mapping(source = "seat.room.roomId", target = "roomId")
    @Mapping(source = "seat.room.column", target = "seatColumn")
    @Mapping(source = "seat.room.row", target = "seatRow")
    SeatShowtimeResponse toSeatShowtimeResponse(SeatShowtime seatShowtime);
    List<SeatShowtimeResponse> toSeatShowtimesResponse(List<SeatShowtime> seatShowtimes);
}
