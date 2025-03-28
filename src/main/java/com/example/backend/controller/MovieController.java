package com.example.backend.controller;

import com.example.backend.entity.Movie;
import com.example.backend.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<Movie> findById(@PathVariable("id") int movieId) {
        return ResponseEntity.ok(movieService.findById(movieId));
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
}
