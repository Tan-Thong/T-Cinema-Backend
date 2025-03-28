package com.example.backend.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@NoArgsConstructor
@Table(name = "tickets")
public class Ticket {
    @Id
    @Column(name = "ticket_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ticketId;
    @Column(name = "ticket_price")
    private Double ticketPrice;

    @ManyToOne
    @JoinColumn(name = "showtime_id")
    private Showtime showtime;

    @ManyToMany(mappedBy = "tickets")
    private List<Seat> seats;

    @ManyToOne
    @JoinColumn(name = "booking_id")
    private Booking booking;
}
