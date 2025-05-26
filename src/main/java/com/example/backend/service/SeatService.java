package com.example.backend.service;

import com.example.backend.dto.response.RoomResponse;
import com.example.backend.dto.response.SeatResponse;
import com.example.backend.entity.Seat;
import com.example.backend.mapper.SeatMapper;
import com.example.backend.repository.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeatService {
    @Autowired
    private SeatRepository seatRepository;

    @Autowired
    private SeatMapper seatMapper;

    public List<SeatResponse> getSeats() {
        return seatMapper.toSeatsResponse(seatRepository.findAll());
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
        seat.setRoom(seat.getRoom());
        return seatRepository.save(seat);
    }

    public void deleteSeat(int seatId) {
        seatRepository.delete(seatRepository.findById(seatId).orElseThrow(()->new RuntimeException("Ghế không tồn tại!")));
    }

    public List<SeatResponse> getSeatsByRoomId(int roomId) {
        return seatMapper.toSeatsResponse(seatRepository.findByRoom_RoomId(roomId));
    }
}
