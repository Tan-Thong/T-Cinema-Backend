package com.example.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@NoArgsConstructor
@Table(name = "cinemas")
public class Cinema {
    @Id
    @Column(name = "cinema_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cinemaId;
    @Column(name = "cinema_name")
    private String cinemaName;
    private String city;
    private String location;

    @JsonIgnore
    @OneToMany(mappedBy = "cinema")
    private List<Room> rooms;
}
