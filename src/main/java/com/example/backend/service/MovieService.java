package com.example.backend.service;

import com.example.backend.entity.Movie;
import com.example.backend.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {
    @Autowired
    private MovieRepository movieRepository;

    public List<Movie> findAll() {
        return movieRepository.findAll();
    }

    public List<Movie> findByIdNot(int movieId) {
        return movieRepository.findByMovieIdNot(movieId);
    }

    public List<Movie> findUpcomingMovies() {
        return movieRepository.findUpcomingMovies();
    }

    public List<Movie> findShowingMovies() {
        return movieRepository.findShowingMovies();
    }

    public Movie findById(int movieId) {
        return movieRepository.findById(movieId).get();
    }
}
