package com.example.backend.mapper;

import com.example.backend.dto.request.MovieCreationRequest;
import com.example.backend.dto.request.MovieUpdateRequest;
import com.example.backend.entity.Movie;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface MovieMapper {
    Movie createMovie(MovieCreationRequest request);
    void updateMovie(@MappingTarget Movie movie, MovieUpdateRequest request);
}
