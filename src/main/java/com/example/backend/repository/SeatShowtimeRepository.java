package com.example.backend.repository;

import com.example.backend.entity.SeatShowtime;
import com.example.backend.entity.SeatShowtimeId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeatShowtimeRepository extends JpaRepository<SeatShowtime, SeatShowtimeId> {
}
