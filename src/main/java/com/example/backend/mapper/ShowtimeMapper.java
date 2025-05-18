package com.example.backend.mapper;

import com.example.backend.dto.request.MovieUpdateRequest;
import com.example.backend.dto.request.ShowtimeRequest;
import com.example.backend.dto.response.ShowtimeResponse;
import com.example.backend.entity.Movie;
import com.example.backend.entity.Showtime;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ShowtimeMapper {
    Showtime toShowtime(ShowtimeRequest showtimeRequest);
    @Mapping(source = "room", target = "room")
    @Mapping(source = "movie", target = "movie")
    ShowtimeResponse toShowtimeResponse(Showtime showtime);
    void updateShowtime(@MappingTarget Showtime showtime, ShowtimeRequest showtimeRequest);
    List<ShowtimeResponse> toShowtimesResponse(List<Showtime> showtimes);
}
