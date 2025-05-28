package com.example.backend.controller;

import com.example.backend.dto.request.CinemaRequest;
import com.example.backend.dto.response.ApiResponse;
import com.example.backend.dto.response.CinemaResponse;
import com.example.backend.dto.response.RoomResponse;
import com.example.backend.entity.Cinema;
import com.example.backend.service.CinemaService;
import com.example.backend.service.RoomService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cinemas")
public class CinemaController {
    @Autowired
    private CinemaService cinemaService;
    @Autowired
    private RoomService roomService;

    @GetMapping()
    public ResponseEntity<List<Cinema>> findAll() {
        return ResponseEntity.ok(cinemaService.getCinemas());
    }

    @GetMapping("/{cinemaId}/rooms")
    public ApiResponse<List<RoomResponse>> getRooms(@PathVariable int cinemaId) {
        ApiResponse<List<RoomResponse>> apiResponse = new ApiResponse<>();
        apiResponse.setResult(roomService.getRoomsByCinemaId(cinemaId));
        return apiResponse;
    }

    @PostMapping()
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public ApiResponse<CinemaResponse> createCinema(@RequestBody @Valid CinemaRequest cinemaRequest) {
        ApiResponse<CinemaResponse> apiResponse = new ApiResponse<>();
        apiResponse.setResult(cinemaService.createCinema(cinemaRequest));
        return apiResponse;
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public ApiResponse<CinemaResponse> updateCinema(@PathVariable("id") int cinemaId, @RequestBody @Valid CinemaRequest cinemaRequest) {
        ApiResponse<CinemaResponse> apiResponse = new ApiResponse<>();
        apiResponse.setResult(cinemaService.updateCinema(cinemaId, cinemaRequest));
        return apiResponse;
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
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
