package com.example.backend.service;

import com.example.backend.dto.request.MovieCreationRequest;
import com.example.backend.dto.request.MovieUpdateRequest;
import com.example.backend.entity.Movie;
import com.example.backend.exception.AppException;
import com.example.backend.exception.ErrorCode;
import com.example.backend.mapper.MovieMapper;
import com.example.backend.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {
    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private MovieMapper movieMapper;

    public List<Movie> getMovies() {
        return movieRepository.findAll();
    }

    public Movie getMovie(int movieId) {
        return movieRepository.findById(movieId).orElseThrow(() -> new AppException(ErrorCode.MOVIE_NOT_EXISTED));
    }

    public Movie createMovie(MovieCreationRequest request) {
        Movie movie = movieMapper.createMovie(request);
        return movieRepository.save(movie);
    }

    public Movie updateMovie(int movieId, MovieUpdateRequest request) {
        Movie movie = getMovie(movieId);
        movieMapper.updateMovie(movie, request);
        return movieRepository.save(movie);
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
}
