package com.example.backend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class SeatShowtimeId implements Serializable {
    @Column(name = "seat_id")
    private int seatId;

    @Column(name = "showtime_id")
    private int showtimeId;
}

