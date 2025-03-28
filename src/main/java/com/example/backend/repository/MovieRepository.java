package com.example.backend.repository;

import com.example.backend.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {
    List<Movie> findByMovieIdNot (int movieId);

    @Query("SELECT m FROM Movie m WHERE m.releaseDate > CURRENT_DATE")
    List<Movie> findUpcomingMovies ();

    @Query("SELECT m FROM Movie m WHERE m.releaseDate <= CURRENT_DATE")
    List<Movie> findShowingMovies ();
}
