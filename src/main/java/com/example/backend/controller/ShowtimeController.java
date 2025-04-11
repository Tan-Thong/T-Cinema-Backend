package com.example.backend.controller;

import com.example.backend.dto.ShowtimeDTOv2;
import com.example.backend.dto.ShowtimeDTOv1;
import com.example.backend.entity.Showtime;
import com.example.backend.service.ShowtimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/showtimes")
public class ShowtimeController {
    @Autowired
    private ShowtimeService showtimeService;

    @GetMapping("/movie/{id}")
    public ResponseEntity<List<Showtime>> findByMovieId(@PathVariable("id") int movieId) {
        return  ResponseEntity.ok(showtimeService.findByMovieId(movieId));
    }

    @GetMapping("/v2/movie/{id}")
    public ResponseEntity<List<ShowtimeDTOv1>> findCinemasAndRoomsByMovieId(@PathVariable("id") int movieId) {
        return ResponseEntity.ok(showtimeService.findCinemasAndRoomsByMovieId(movieId));
    }

    @GetMapping("/v3")
    public ResponseEntity<List<ShowtimeDTOv1>> findCinemasAndRoomsByMovieId(@RequestParam int movieId, @RequestParam String showDate) {
        return ResponseEntity.ok(showtimeService.getShowtimesByMovieAndDate(movieId, showDate));
    }

    @GetMapping("")
    public ResponseEntity<ShowtimeDTOv2> find(@RequestParam int movieId, @RequestParam int showtimeId) {
        return ResponseEntity.ok(showtimeService.getShowtimeById(movieId, showtimeId));
    }

    @GetMapping("/v4")
    public ResponseEntity<List<ShowtimeDTOv2>> findByMovieAndDate(@RequestParam int movieId, @RequestParam String showDate) {
        return ResponseEntity.ok(showtimeService.findByMovieAndDate(movieId, LocalDate.parse(showDate)));
    }
}
