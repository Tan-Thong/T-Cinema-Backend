package com.example.backend.controller;

import com.example.backend.dto.request.HoldSeatRequest;
import com.example.backend.dto.response.ApiResponse;
import com.example.backend.dto.response.SeatShowtimeResponse;
import com.example.backend.entity.SeatShowtime;
import com.example.backend.service.SeatShowtimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{showtimeId}/seats")
    public ApiResponse<List<SeatShowtimeResponse>> getSeatsByShowtimeId(@PathVariable("showtimeId") int showtimeId) {
        ApiResponse<List<SeatShowtimeResponse>> apiResponse = new ApiResponse<>();
        apiResponse.setResult(seatShowtimeService.getSeatsByShowtimeId(showtimeId));
        return apiResponse;
    }

    @PostMapping("/hold")
    public void holdSeats(@RequestBody HoldSeatRequest holdSeatRequest) {
        System.out.println(holdSeatRequest);
        seatShowtimeService.holdSeats(holdSeatRequest);
    }
}
