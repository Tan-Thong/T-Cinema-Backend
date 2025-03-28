package com.example.backend.repository;

import com.example.backend.entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeatRepository  extends JpaRepository<Seat, Integer> {
}
