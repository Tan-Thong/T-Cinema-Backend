package com.example.backend.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class ShowtimeDTOv1 {
    private int showtimeId;
    private String cinemaCity;
    private String cinemaName;
    private String roomName;
    private List<String> showtimes = new ArrayList<>();

    public ShowtimeDTOv1(int showtimeId, String cinemaCity, String cinemaName, String roomName, List<String> showtimes) {
        this.showtimeId = showtimeId;
        this.cinemaCity = cinemaCity;
        this.cinemaName = cinemaName;
        this.roomName = roomName;
        this.showtimes = showtimes;
    }

    public List<String> getShowtimes() {
        return showtimes;
    }
}
