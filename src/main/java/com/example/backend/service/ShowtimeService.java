package com.example.backend.service;

import com.example.backend.dto.request.ShowtimeRequest;
import com.example.backend.dto.response.ShowtimeResponse;
import com.example.backend.entity.*;
import com.example.backend.enums.SeatStatus;
import com.example.backend.mapper.ShowtimeMapper;
import com.example.backend.repository.*;
import jakarta.transaction.Transactional;
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
    private SeatRepository seatRepository;

    @Autowired
    private SeatShowtimeRepository seatShowtimeRepository;

    @Autowired
    private ShowtimeMapper showtimeMapper;

    public List<ShowtimeResponse> getShowtimesByMovieAndDate(int movieId, String showDate) {
        return showtimeMapper.toShowtimesResponse(showtimeRepository.findShowtimesByMovieAndDate(movieId, LocalDate.parse(showDate)));
    }

    public ShowtimeResponse createShowtime(ShowtimeRequest showtimeRequest) {
        Showtime showtime = showtimeMapper.toShowtime(showtimeRequest);

        Movie movie = movieRepository.findById(showtimeRequest.getMovieId())
                .orElseThrow(() -> new RuntimeException("Phim không tồn tại!"));
        Room room = roomRepository.findById(showtimeRequest.getRoomId())
                .orElseThrow(() -> new RuntimeException("Phòng không tồn tại!"));

        showtime.setMovie(movie);
        showtime.setRoom(room);

        // Lưu và nhận lại đối tượng đã lưu (có ID)
        Showtime savedShowtime = showtimeRepository.save(showtime);
        List<Seat> seats = seatRepository.findByRoom_RoomId(room.getRoomId());
        System.out.println(room.getRoomId());

        for (Seat seat : seats) {
            SeatShowtime seatShowtime = new SeatShowtime();
            SeatShowtimeId seatShowtimeId = new SeatShowtimeId(seat.getSeatId(), savedShowtime.getShowtimeId());
            seatShowtime.setId(seatShowtimeId);
            seatShowtime.setSeatStatus(SeatStatus.AVAILABLE);
            seatShowtimeRepository.save(seatShowtime);
        }

        return showtimeMapper.toShowtimeResponse(savedShowtime);
    }

    @Transactional
    public void deleteShowtime(Long showtimeId) {
        // Kiểm tra xem suất chiếu có tồn tại không
        Showtime showtime = showtimeRepository.findById(showtimeId)
                .orElseThrow(() -> new RuntimeException("Suất chiếu không tồn tại!"));

        // Xoá toàn bộ bản ghi SeatShowtime liên quan
        seatShowtimeRepository.deleteById_ShowtimeId(showtimeId);

        // Xoá suất chiếu
        showtimeRepository.deleteById(showtimeId);
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
