package com.example.backend.repository;

import com.example.backend.entity.SeatShowtime;
import com.example.backend.entity.SeatShowtimeId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeatShowtimeRepository extends JpaRepository<SeatShowtime, SeatShowtimeId> {
    List<SeatShowtime> findByIdShowtimeId(int showtimeId);
    void deleteById_ShowtimeId(Long showtimeId);
}
