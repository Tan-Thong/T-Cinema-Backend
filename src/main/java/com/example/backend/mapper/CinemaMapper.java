package com.example.backend.mapper;

import com.example.backend.dto.request.CinemaRequest;
import com.example.backend.dto.response.CinemaResponse;
import com.example.backend.entity.Cinema;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CinemaMapper {
    Cinema toCinema(CinemaRequest cinemaRequest);
    CinemaResponse toCinemaRespone(Cinema cinema);
    void updateCinema(@MappingTarget Cinema cinema, CinemaRequest request);
}
