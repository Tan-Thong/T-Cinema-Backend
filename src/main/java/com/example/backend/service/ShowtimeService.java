package com.example.backend.service;

import com.example.backend.dto.request.ShowtimeRequest;
import com.example.backend.dto.response.ShowtimeResponse;
import com.example.backend.entity.Movie;
import com.example.backend.entity.Room;
import com.example.backend.entity.Showtime;
import com.example.backend.mapper.ShowtimeMapper;
import com.example.backend.repository.MovieRepository;
import com.example.backend.repository.RoomRepository;
import com.example.backend.repository.ShowtimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ShowtimeService {
    @Autowired
    private ShowtimeRepository showtimeRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private ShowtimeMapper showtimeMapper;

    public List<ShowtimeResponse> getShowtimesByMovieAndDate(int movieId, String showDate) {
        return showtimeMapper.toShowtimesResponse(showtimeRepository.findShowtimesByMovieAndDate(movieId, LocalDate.parse(showDate)));
    }

    public ShowtimeResponse createShowtime(ShowtimeRequest showtimeRequest) {
        Showtime showtime = showtimeMapper.toShowtime(showtimeRequest);
        Movie movie = movieRepository.findById(showtimeRequest.getMovieId()).orElseThrow(() -> new RuntimeException("Phim không tồn tại!"));
        Room room = roomRepository.findById(showtimeRequest.getRoomId()).orElseThrow(() -> new RuntimeException("Phòng không tồn tại!"));
        showtime.setMovie(movie);
        showtime.setRoom(room);
        return showtimeMapper.toShowtimeResponse(showtimeRepository.save(showtime));
    }

    public ShowtimeResponse updateShowtime(int showtimeId, ShowtimeRequest showtimeRequest) {
        Showtime showtime = showtimeRepository.findById(showtimeId).orElseThrow(() -> new RuntimeException("Suât chiếu không tồn tại!"));
        showtimeMapper.updateShowtime(showtime, showtimeRequest);
        Movie movie = movieRepository.findById(showtimeRequest.getMovieId()).orElseThrow(() -> new RuntimeException("Phim không tồn tại!"));
        Room room = roomRepository.findById(showtimeRequest.getRoomId()).orElseThrow(() -> new RuntimeException("Phòng không tồn tại!"));
        showtime.setMovie(movie);
        showtime.setRoom(room);
        return showtimeMapper.toShowtimeResponse(showtimeRepository.save(showtime));
    }

    public List<ShowtimeResponse> getShowtimes() {
        return showtimeMapper.toShowtimesResponse(showtimeRepository.findAll());
    }

    public ShowtimeResponse getShowtime(int showtimeId) {
        return showtimeMapper.toShowtimeResponse(showtimeRepository.findById(showtimeId).orElseThrow(() -> new RuntimeException("Suất chiếu không tồn tại!")));
    }
}
