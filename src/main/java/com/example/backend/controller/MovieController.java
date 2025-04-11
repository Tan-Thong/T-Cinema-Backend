package com.example.backend.controller;

import com.example.backend.dto.request.MovieCreationRequest;
import com.example.backend.dto.response.ApiResponse;
import com.example.backend.entity.Movie;
import com.example.backend.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Date;
import java.util.List;

@RestController
public class MovieController {
    @Autowired
    private MovieService movieService;

    @GetMapping("movies")
    public ResponseEntity<List<Movie>> findAll() {
        return ResponseEntity.ok(movieService.findAll());
    }

    @GetMapping("movies/{id}")
    public ApiResponse<Movie> findById(@PathVariable("id") int movieId) {
        ApiResponse<Movie> apiResponse = new ApiResponse<>();
        apiResponse.setResult(movieService.findById(movieId));
        return apiResponse;
    }

    @GetMapping("movies/not/{id}")
    public ResponseEntity<List<Movie>> findByIdNot(@PathVariable("id") int movieId) {
        return ResponseEntity.ok(movieService.findByIdNot(movieId));
    }

    @GetMapping("movies/upcoming")
    public ResponseEntity<List<Movie>> findUpcomingMovies() {
        return ResponseEntity.ok(movieService.findUpcomingMovies());
    }

    @GetMapping("movies/showing")
    public ResponseEntity<List<Movie>> findShowingMovies() {
        return ResponseEntity.ok(movieService.findShowingMovies());
    }

    @PostMapping(value = "/movies")
    public ApiResponse<Movie> createMovieV2(@RequestBody MovieCreationRequest request) {

        ApiResponse<Movie> apiResponse = new ApiResponse<>();
        apiResponse.setResult(movieService.createMovie(request));
        return apiResponse;
    }
}
