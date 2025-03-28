package com.example.backend.controller;

import com.example.backend.entity.Cinema;
import com.example.backend.service.CinemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cinemas")
public class CinemaController {
    @Autowired
    private CinemaService cinemaService;

    @GetMapping()
    public ResponseEntity<List<Cinema>> findAll() {
        return ResponseEntity.ok(cinemaService.findAll());
    }

    @GetMapping("/movie/{id}")
    public ResponseEntity<List<Cinema>> findByMovieId(@PathVariable("id") int movieId) {
        return ResponseEntity.ok(cinemaService.findByMovieId(movieId));
    }
}
