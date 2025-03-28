package com.example.backend.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@Table(name = "bookings")
public class Booking {
    @Id
    @Column(name = "booking_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bookingId;
    @Column(name = "booking_date")
    private Date bookingDate;
    @Column(name = "total_price")
    private Double totalPrice;
    private Status status;

    @OneToMany(mappedBy = "booking")
    private List<Ticket> tickets;

    @OneToOne
    @JoinColumn(name = "payment_id")
    private Payment payment;
}
