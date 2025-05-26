package com.example.backend.controller;

import com.example.backend.dto.response.ApiResponse;
import com.example.backend.dto.response.SeatResponse;
import com.example.backend.entity.Seat;
import com.example.backend.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("seats")
public class SeatController {
    @Autowired
    private SeatService seatService;

    @GetMapping("")
    public ApiResponse<List<SeatResponse>> getSeats() {
        ApiResponse<List<SeatResponse>> apiResponse = new ApiResponse<>();
        apiResponse.setResult(seatService.getSeats());
        return apiResponse;
    }
}
