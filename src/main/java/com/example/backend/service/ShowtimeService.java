package com.example.backend.service;

import com.example.backend.dto.ShowtimeDTOv2;
import com.example.backend.dto.ShowtimeDTOv1;
import com.example.backend.entity.Showtime;
import com.example.backend.repository.ShowtimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ShowtimeService {
    @Autowired
    private ShowtimeRepository showtimeRepository;

    public List<Showtime> findByMovieId(int movieId) {
        return showtimeRepository.findByMovieId(movieId);
    }

    public List<ShowtimeDTOv1> findCinemasAndRoomsByMovieId(int movieId) {
        List<Object[]> objects = showtimeRepository.findCinemasAndRoomsByMovieId(movieId);
        Map<String, ShowtimeDTOv1> groupedShowtimes = new HashMap<>();

        for (Object[] object : objects) {
            int showtimeId = (int) object[0];
            String cinemaCity = (String) object[1];
            String cinemaName = (String) object[2];
            String roomName = (String) object[3];
            String showtime = (String) object[4]; // Giả sử giờ chiếu là String

            String key = cinemaName + "-" + roomName; // Tạo key để nhóm

            if (!groupedShowtimes.containsKey(key)) {
                groupedShowtimes.put(key, new ShowtimeDTOv1(showtimeId, cinemaCity, cinemaName, roomName, new ArrayList<>()));
            }

            groupedShowtimes.get(key).getShowtimes().add(showtime);
        }

        return new ArrayList<>(groupedShowtimes.values());
    }

    public List<ShowtimeDTOv1> getShowtimesByMovieAndDate(int movieId, String showDate) {
        List<Object[]> objects = showtimeRepository.findShowtimesByMovieAndDate(movieId, LocalDate.parse(showDate));
        Map<String, ShowtimeDTOv1> groupedShowtimes = new HashMap<>();

        for (Object[] object : objects) {
            int showtimeId = (int) object[0];
            String cinemaCity = (String) object[1];
            String cinemaName = (String) object[2];
            String roomName = (String) object[3];
            String showtime = (String) object[4]; // Giả sử giờ chiếu là String

            String key = cinemaName + "-" + roomName; // Tạo key để nhóm

            if (!groupedShowtimes.containsKey(key)) {
                groupedShowtimes.put(key, new ShowtimeDTOv1(showtimeId, cinemaCity, cinemaName, roomName, new ArrayList<>()));
            }

            groupedShowtimes.get(key).getShowtimes().add(showtime);
        }

        return new ArrayList<>(groupedShowtimes.values());
    }

    public List<ShowtimeDTOv2> findByMovieAndDate(int movieId, LocalDate showDate) {
        List<Object[]> objects = showtimeRepository.findByMovieAndDate(movieId, showDate);
        List<ShowtimeDTOv2> showtimes = new ArrayList<>();
        for (Object[] object : objects) {
            ShowtimeDTOv2 showtimeDTOv2 = new ShowtimeDTOv2();
            showtimeDTOv2.setShowtimeId((int) object[0]);
            showtimeDTOv2.setCinemaCity((String) object[1]);
            showtimeDTOv2.setCinemaName((String) object[2]);
            showtimeDTOv2.setRoomName((String) object[3]);
            showtimeDTOv2.setShowDate((LocalDate) object[4]);
            showtimeDTOv2.setShowTime((String) object[5]);

            showtimes.add(showtimeDTOv2);
        }
        return showtimes;
    }

    public ShowtimeDTOv2 getShowtimeById(int movieId, int showtimeId) {
        List<Object[]> objects = showtimeRepository.findShowtimeById(movieId, showtimeId);
        ShowtimeDTOv2 bookingDTO = new ShowtimeDTOv2();
        for (Object[] object : objects) {
            int showtimeID = (int) object[0];
            String cinemaCity = (String) object[1];
            String cinemaName = (String) object[2];
            String roomName = (String) object[3];
            LocalDate showDate = (LocalDate) object[4];
            String showtime = (String) object[5]; // Giả sử giờ chiếu là String

            bookingDTO = new ShowtimeDTOv2(showtimeID, cinemaCity, cinemaName, roomName, showDate, showtime);
        }
        return bookingDTO;
    }
}
