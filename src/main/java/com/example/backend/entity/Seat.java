package com.example.backend.entity;

import com.example.backend.enums.SeatStatus;
import com.example.backend.enums.SeatType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@Table(name = "seats")
@NoArgsConstructor
public class Seat {
    @Id
    @Column(name = "seat_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int seatId;
    @Column(name = "seat_row")
    private char seatRow;
    @Column(name = "seat_column")
    private int seatColumn;
    @Column(name = "seat_type")
    @Enumerated(EnumType.STRING)
    private SeatType seatType;
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private SeatStatus status;

    @JsonIgnore
    @ManyToMany(mappedBy = "seats")
    private List<Room> rooms;

    @JsonIgnore
    @ManyToMany
    @JoinTable(joinColumns = @JoinColumn(name = "seat_id"), inverseJoinColumns = @JoinColumn(name = "ticket_id"))
    private List<Ticket> tickets;
}
