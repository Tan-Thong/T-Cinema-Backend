package com.example.backend.controller;

import com.example.backend.dto.request.CinemaRequest;
import com.example.backend.dto.response.ApiResponse;
import com.example.backend.dto.response.CinemaResponse;
import com.example.backend.entity.Cinema;
import com.example.backend.service.CinemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cinemas")
public class CinemaController {
    @Autowired
    private CinemaService cinemaService;

    @GetMapping()
    public ResponseEntity<List<Cinema>> findAll() {
        return ResponseEntity.ok(cinemaService.getCinemas());
    }

    @PostMapping()
    public ApiResponse<CinemaResponse> createCinema(@RequestBody CinemaRequest cinemaRequest) {
        ApiResponse<CinemaResponse> apiResponse = new ApiResponse<>();
        apiResponse.setResult(cinemaService.createCinema(cinemaRequest));
        return apiResponse;
    }

    @PutMapping("/{id}")
    public ApiResponse<CinemaResponse> updateCinema(@PathVariable("id") int cinemaId, @RequestBody CinemaRequest cinemaRequest) {
        ApiResponse<CinemaResponse> apiResponse = new ApiResponse<>();
        apiResponse.setResult(cinemaService.updateCinema(cinemaId, cinemaRequest));
        return apiResponse;
    }

    @DeleteMapping("/{id}")
    public ApiResponse<CinemaResponse> deleteCinema(@PathVariable("id") int cinemaId) {
        ApiResponse<CinemaResponse> apiResponse = new ApiResponse<>();
        cinemaService.deleteCinema(cinemaId);
        return apiResponse;
    }

    @GetMapping("/movie/{id}")
    public ResponseEntity<List<Cinema>> findByMovieId(@PathVariable("id") int movieId) {
        return ResponseEntity.ok(cinemaService.findByMovieId(movieId));
    }
}
