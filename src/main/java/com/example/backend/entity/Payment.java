package com.example.backend.entity;

import com.example.backend.enums.PaymentMethod;
import com.example.backend.enums.Status;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name = "payments")
public class Payment {
    @Id
    @Column(name = "payment_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int paymentId;
    @Column(name = "payment_name")
    private String paymentName;
    @Column(name = "payment_method")
    private PaymentMethod paymentMethod;
    private Status status;

    @OneToOne(mappedBy = "payment")
    private Booking booking;
}
