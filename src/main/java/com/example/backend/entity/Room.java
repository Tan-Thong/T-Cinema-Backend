package com.example.backend.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@NoArgsConstructor
@Table(name = "rooms")
public class Room {
    @Id
    @Column(name = "room_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int roomId;
    @Column(name = "room_name")
    private String roomName;
    @Column(name = "seat_capacity")
    private int seatCapacity;

    @ManyToOne()
    @JoinColumn(name = "cinema_id")
    private Cinema cinema;

    @ManyToMany
    @JoinTable(joinColumns = @JoinColumn(name = "room_id"), inverseJoinColumns = @JoinColumn(name = "seat_id") )
    private List<Seat> seats;

    @OneToMany(mappedBy = "room")
    private List<Showtime> showtimes;
}
