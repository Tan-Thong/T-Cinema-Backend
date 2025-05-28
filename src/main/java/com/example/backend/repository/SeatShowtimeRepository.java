package com.example.backend.repository;

import com.example.backend.entity.SeatShowtime;
import com.example.backend.entity.SeatShowtimeId;
import com.example.backend.enums.SeatStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface SeatShowtimeRepository extends JpaRepository<SeatShowtime, SeatShowtimeId> {
    List<SeatShowtime> findByIdShowtimeId(int showtimeId);
}
