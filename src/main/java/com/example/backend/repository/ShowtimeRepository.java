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
    @Query("SELECT s FROM Showtime s " +
            "JOIN s.movie m " +
            "WHERE m.movieId = :movieId")
    List<Showtime> findByMovieId(@Param("movieId") int movieId);

    @Query("SELECT DISTINCT s.room.cinema.city, s.room.cinema.cinemaName, s.room.roomName, s.showTime FROM Showtime s WHERE s.movie.movieId = :movieId")
    List<Object[]> findCinemasAndRoomsByMovieId(@Param("movieId") int movieId);

    @Query("SELECT DISTINCT s.showtimeId, s.room.cinema.city, s.room.cinema.cinemaName, s.room.roomName, s.showTime " +
            "FROM Showtime s WHERE s.movie.movieId = :movieId AND s.showDate = :showDate")
    List<Object[]> findShowtimesByMovieAndDate(@Param("movieId") int movieId,
                                               @Param("showDate") LocalDate showDate);

    @Query("SELECT DISTINCT s.showtimeId, s.room.cinema.city, s.room.cinema.cinemaName, s.room.roomName, s.showDate, s.showTime " +
            "FROM Showtime s WHERE s.movie.movieId = :movieId AND s.showtimeId = :showtimeId")
    List<Object[]> findShowtimeById(@Param("movieId") int movieId, @Param("showtimeId") int showtimeId);

    @Query("SELECT s.showtimeId, s.room.cinema.city, s.room.cinema.cinemaName, s.room.roomName, s.showDate, s.showTime " +
            "FROM Showtime s " +
            "WHERE s.movie.movieId = :movieId AND s.showDate = :showDate")
    List<Object[]> findByMovieAndDate(@Param("movieId") int movieId, @Param("showDate") LocalDate showDate);
}
