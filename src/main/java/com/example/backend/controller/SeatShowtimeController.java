package com.example.backend.controller;

import com.example.backend.dto.response.ApiResponse;
import com.example.backend.dto.response.SeatShowtimeResponse;
import com.example.backend.entity.SeatShowtime;
import com.example.backend.service.SeatShowtimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("seat-showtime")
public class SeatShowtimeController {
    @Autowired
    private SeatShowtimeService seatShowtimeService;

    @GetMapping("")
    public ApiResponse<List<SeatShowtimeResponse>> getAllSeatShowtime() {
        ApiResponse<List<SeatShowtimeResponse>> apiResponse = new ApiResponse<>();
        apiResponse.setResult(seatShowtimeService.getSeatShowtimes());
        return apiResponse;
    }

}
