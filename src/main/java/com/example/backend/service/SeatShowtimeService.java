package com.example.backend.service;

import com.example.backend.dto.request.HoldSeatRequest;
import com.example.backend.dto.response.SeatShowtimeResponse;
import com.example.backend.entity.SeatShowtime;
import com.example.backend.entity.SeatShowtimeId;
import com.example.backend.enums.SeatStatus;
import com.example.backend.mapper.SeatShowtimeMapper;
import com.example.backend.repository.SeatShowtimeRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class SeatShowtimeService {
    @Autowired
    private SeatShowtimeRepository seatShowtimeRepository;

    @Autowired
    private SeatShowtimeMapper seatShowtimeMapper;

    public List<SeatShowtimeResponse> getSeatShowtimes() {
        return seatShowtimeMapper.toSeatShowtimesResponse(seatShowtimeRepository.findAll());
    }

    public List<SeatShowtimeResponse> getSeatsByShowtimeId(int showtimeId) {
        return seatShowtimeMapper.toSeatShowtimesResponse(seatShowtimeRepository.findByIdShowtimeId(showtimeId));
    }

    @Transactional
    public void holdSeats(HoldSeatRequest holdSeatRequest) {
        SeatShowtimeId id = new SeatShowtimeId(holdSeatRequest.getSeatId(), holdSeatRequest.getShowtimeId());
        SeatShowtime seatShowtime = seatShowtimeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ghế không tồn tại"));

//            if (seatShowtime.getSeatStatus() != SeatStatus.AVAILABLE) {
//                return ResponseEntity.status(HttpStatus.CONFLICT).body("Ghế đã được giữ hoặc đặt");
//            }

        seatShowtime.setSeatStatus(SeatStatus.HELD);
        seatShowtimeRepository.save(seatShowtime);
    }
}
