package com.example.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@Table(name = "showtimes")
public class Showtime {
    @Id
    @Column(name = "showtime_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int showtimeId;
    @Column(name = "show_date")
    private LocalDate showDate;
    @Column(name = "show_time")
    private String showTime;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;

    @JsonIgnore
    @OneToMany(mappedBy = "showtime")
    private List<Ticket> tickets;
}
