package com.example.backend.entity;

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
    @Column(name = "seat_number")
    private String seatNumber;
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
