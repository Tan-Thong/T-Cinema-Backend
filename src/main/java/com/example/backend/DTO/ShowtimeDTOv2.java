package com.example.backend.DTO;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class ShowtimeDTOv2 {
    private int showtimeId;
    private String cinemaCity;
    private String cinemaName;
    private String roomName;
    private LocalDate showDate;
    private String showTime;

    public ShowtimeDTOv2(int showtimeId, String cinemaCity, String cinemaName, String roomName, LocalDate showDate, String showTime) {
        this.showtimeId = showtimeId;
        this.showTime = showTime;
        this.showDate = showDate;
        this.roomName = roomName;
        this.cinemaName = cinemaName;
        this.cinemaCity = cinemaCity;
    }

    public ShowtimeDTOv2() {
    }

    public int getShowtimeId() {
        return showtimeId;
    }

    public String getCinemaCity() {
        return cinemaCity;
    }

    public String getCinemaName() {
        return cinemaName;
    }

    public String getRoomName() {
        return roomName;
    }

    public LocalDate getShowDate() {
        return showDate;
    }

    public String getShowTime() {
        return showTime;
    }

    public void setShowTime(String showTime) {
        this.showTime = showTime;
    }

    public void setShowDate(LocalDate showDate) {
        this.showDate = showDate;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public void setCinemaName(String cinemaName) {
        this.cinemaName = cinemaName;
    }

    public void setCinemaCity(String cinemaCity) {
        this.cinemaCity = cinemaCity;
    }

    public void setShowtimeId(int showtimeId) {
        this.showtimeId = showtimeId;
    }
}
