package com.example.backend.mapper;

import com.example.backend.dto.request.MovieCreationRequest;
import com.example.backend.entity.Movie;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MovieMapper {
    Movie toMovie(MovieCreationRequest request);
}
