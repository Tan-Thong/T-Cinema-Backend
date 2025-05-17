package com.example.backend.repository;

import com.example.backend.entity.Showtime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ShowtimeRepository extends JpaRepository<Showtime, Integer> {
    @Query("SELECT s FROM Showtime s WHERE s.movie.movieId = :movieId AND s.showDate = :showDate")
    List<Showtime> findShowtimesByMovieAndDate(@Param("movieId") int movieId, @Param("showDate") LocalDate showDate);
}
