package com.example.backend.controller;

import com.example.backend.dto.request.ShowtimeRequest;
import com.example.backend.dto.response.ApiResponse;
import com.example.backend.dto.response.ShowtimeResponse;
import com.example.backend.service.ShowtimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/showtimes")
public class ShowtimeController {
    @Autowired
    private ShowtimeService showtimeService;

    @GetMapping()
    public ApiResponse<List<ShowtimeResponse>> getShowtimes() {
        ApiResponse<List<ShowtimeResponse>> apiResponse = new ApiResponse<>();
        apiResponse.setResult(showtimeService.getShowtimes());
        return apiResponse;
    }

    @GetMapping("/{id}")
    public ApiResponse<ShowtimeResponse> getShowtime(@PathVariable("id") int showtimeId) {
        ApiResponse<ShowtimeResponse> apiResponse = new ApiResponse<>();
        apiResponse.setResult(showtimeService.getShowtime(showtimeId));
        return apiResponse;
    }

    @PostMapping()
    public ApiResponse<ShowtimeResponse> createShowtime(@RequestBody ShowtimeRequest showtimeRequest) {
        ApiResponse<ShowtimeResponse> apiResponse = new ApiResponse<>();
        ShowtimeResponse showtimeResponse = showtimeService.createShowtime(showtimeRequest);
        apiResponse.setResult(showtimeResponse);
        return apiResponse;
    }
}
