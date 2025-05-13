package com.example.backend.service;

import com.example.backend.dto.request.CinemaRequest;
import com.example.backend.dto.response.CinemaResponse;
import com.example.backend.entity.Cinema;
import com.example.backend.mapper.CinemaMapper;
import com.example.backend.repository.CinemaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CinemaService {
    @Autowired
    private CinemaRepository cinemaRepository;

    @Autowired
    private CinemaMapper cinemaMapper;

    public List<Cinema> getCinemas() {
        return cinemaRepository.findAll();
    }

    public CinemaResponse createCinema(CinemaRequest cinemaRequest) {
        Cinema cinema = cinemaMapper.toCinema(cinemaRequest);
        return cinemaMapper.toCinemaRespone(cinemaRepository.save(cinema));
    }

    public CinemaResponse updateCinema(int cinemaId, CinemaRequest cinemaRequest) {
        Cinema cinema = cinemaRepository.findById(cinemaId).orElseThrow(() -> new RuntimeException("Rạp không tồn tại!"));
        cinemaMapper.updateCinema(cinema, cinemaRequest);
        return cinemaMapper.toCinemaRespone(cinemaRepository.save(cinema));
    }

    public void deleteCinema(int cinemaId) {
        Cinema cinema = cinemaRepository.findById(cinemaId).orElseThrow(() -> new RuntimeException("Rạp không tồn tại!"));
        cinemaRepository.delete(cinema);
    }

    public List<Cinema> findByMovieId(int id) {
        return cinemaRepository.findByMovieId(id);
    }
}
