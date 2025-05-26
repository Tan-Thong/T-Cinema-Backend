package com.example.backend.mapper;

import com.example.backend.dto.response.SeatResponse;
import com.example.backend.entity.Seat;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SeatMapper {
    @Mapping(source = "room", target = "room")
    SeatResponse toSeatResponse(Seat seat);
    List<SeatResponse> toSeatsResponse(List<Seat> seats);
}
