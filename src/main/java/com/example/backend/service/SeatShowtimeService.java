package com.example.backend.service;

import com.example.backend.dto.response.SeatShowtimeResponse;
import com.example.backend.entity.SeatShowtime;
import com.example.backend.mapper.SeatShowtimeMapper;
import com.example.backend.repository.SeatShowtimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
