package com.example.backend.entity;

import com.example.backend.enums.SeatStatus;
import com.example.backend.enums.SeatType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@Table(name = "seats")
@NoArgsConstructor
@AllArgsConstructor
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
    @ManyToOne
    @JoinColumn(name = "roomId")
    private Room room;

    @JsonIgnore
    @ManyToMany
    @JoinTable(joinColumns = @JoinColumn(name = "seat_id"), inverseJoinColumns = @JoinColumn(name = "ticket_id"))
    private List<Ticket> tickets;

    public Seat(char seatRow, int seatColumn, SeatType seatType, SeatStatus status, Room room) {
        this.seatRow = seatRow;
        this.seatColumn = seatColumn;
        this.seatType = seatType;
        this.status = status;
        this.room = room;
    }
}
