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

    public Seat getSeat(int seatId) {
        return seatRepository.findById(seatId).orElseThrow(()->new RuntimeException("Ghế không tồn tại!"));
    }

    public Seat createSeat(Seat seat) {
        return seatRepository.save(seat);
    }

    public Seat updateSeat(int seatId, Seat seatRequest) {
        Seat seat = seatRepository.findById(seatId).orElseThrow(()->new RuntimeException("Ghế không tồn tại!"));
        seat.setSeatRow(seatRequest.getSeatRow());
        seat.setSeatColumn(seatRequest.getSeatColumn());
        seat.setSeatType(seatRequest.getSeatType());
        seat.setStatus(seatRequest.getStatus());
        seat.setRoom(seat.getRoom());
        return seatRepository.save(seat);
    }

    public void deleteSeat(int seatId) {
        seatRepository.delete(seatRepository.findById(seatId).orElseThrow(()->new RuntimeException("Ghế không tồn tại!")));
    }
}
