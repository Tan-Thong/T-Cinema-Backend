package com.example.backend.entity;

import com.example.backend.enums.SeatStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "seat_showtime")
public class SeatShowtime {

    @EmbeddedId
    private SeatShowtimeId id;

    @ManyToOne
    @JoinColumn(name = "seat_id", insertable=false, updatable=false)
    private Seat seat;

    @ManyToOne
    @JoinColumn(name = "showtime_id", insertable=false, updatable=false)
    private Showtime showtime;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private SeatStatus seatStatus;
}

