package com.example.backend.controller;

import com.example.backend.dto.request.MovieCreationRequest;
import com.example.backend.dto.request.MovieUpdateRequest;
import com.example.backend.dto.response.ApiResponse;
import com.example.backend.entity.Movie;
import com.example.backend.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class MovieController {
    @Autowired
    private MovieService movieService;

    @GetMapping("movies")
    public ResponseEntity<List<Movie>> getMovies() {
        return ResponseEntity.ok(movieService.getMovies());
    }

    @GetMapping("movies/{id}")
    public ApiResponse<Movie> getMovie(@PathVariable("id") int movieId) {
        ApiResponse<Movie> apiResponse = new ApiResponse<>();
        apiResponse.setResult(movieService.getMovie(movieId));
        return apiResponse;
    }

    @PostMapping("movies/{id}")
    public ApiResponse<Movie> updateMovie(@PathVariable("id") int movieId, @RequestBody MovieUpdateRequest request) {
        ApiResponse<Movie> apiResponse = new ApiResponse<>();
        apiResponse.setResult(movieService.updateMovie(movieId, request));
        return apiResponse;
    }

    @PostMapping(value = "/movies")
    public ApiResponse<Movie> createMovie(@RequestBody MovieCreationRequest request) {

        ApiResponse<Movie> apiResponse = new ApiResponse<>();
        apiResponse.setResult(movieService.createMovie(request));
        return apiResponse;
    }

    @GetMapping("movies/not/{id}")
    public ResponseEntity<List<Movie>> getByIdNot(@PathVariable("id") int movieId) {
        return ResponseEntity.ok(movieService.findByIdNot(movieId));
    }

    @GetMapping("movies/upcoming")
    public ResponseEntity<List<Movie>> getUpcomingMovies() {
        return ResponseEntity.ok(movieService.findUpcomingMovies());
    }

    @GetMapping("movies/showing")
    public ResponseEntity<List<Movie>> getShowingMovies() {
        return ResponseEntity.ok(movieService.findShowingMovies());
    }
}
