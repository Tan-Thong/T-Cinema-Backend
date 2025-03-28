package com.example.backend.service;

import com.example.backend.entity.Cinema;
import com.example.backend.repository.CinemaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CinemaService {
    @Autowired
    private CinemaRepository cinemaRepository;

    public List<Cinema> findAll() {
        return cinemaRepository.findAll();
    }

    public List<Cinema> findByMovieId(int id) {
        return cinemaRepository.findByMovieId(id);
    }
}
