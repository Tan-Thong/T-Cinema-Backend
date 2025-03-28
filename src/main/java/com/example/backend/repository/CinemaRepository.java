package com.example.backend.repository;

import com.example.backend.entity.Cinema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CinemaRepository extends JpaRepository<Cinema, Integer> {
    @Query("SELECT DISTINCT c " +
            "FROM Cinema c " +
            "JOIN c.rooms r " +
            "JOIN r.showtimes s " +
            "JOIN s.movie m " +
            "WHERE m.movieId = :movieId")
    public List<Cinema> findByMovieId(@Param("movieId") int movieId);
}
