package com.example.backend.controller;

import com.example.backend.dto.request.ShowtimeRequest;
import com.example.backend.dto.response.ApiResponse;
import com.example.backend.dto.response.ShowtimeResponse;
import com.example.backend.service.ShowtimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public ApiResponse<ShowtimeResponse> createShowtime(@RequestBody ShowtimeRequest showtimeRequest) {
        ApiResponse<ShowtimeResponse> apiResponse = new ApiResponse<>();
        ShowtimeResponse showtimeResponse = showtimeService.createShowtime(showtimeRequest);
        apiResponse.setResult(showtimeResponse);
        return apiResponse;
    }

    @PutMapping("{id}")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public ApiResponse<ShowtimeResponse> createShowtime(@PathVariable("id") int showtimeId ,@RequestBody ShowtimeRequest showtimeRequest) {
        ApiResponse<ShowtimeResponse> apiResponse = new ApiResponse<>();
        ShowtimeResponse showtimeResponse = showtimeService.updateShowtime(showtimeId, showtimeRequest);
        apiResponse.setResult(showtimeResponse);
        return apiResponse;
    }
}
