package com.example.backend.service;

import com.example.backend.entity.Seat;
import com.example.backend.repository.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeatService {
    @Autowired
    private SeatRepository seatRepository;

    public List<Seat> findAllSeats() {
        return seatRepository.findAll();
    }
}
