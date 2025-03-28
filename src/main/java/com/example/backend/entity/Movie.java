package com.example.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@Table(name = "movies")
public class Movie {
    @Id
    @Column(name = "movie_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int movieId;
    private String title;
    @Column(name = "trailer_url")
    private String trailerUrl;
    @Column(name = "thumbnail_url")
    private String thumbnailUrl;
    @Column(name = "banner_url")
    private String bannerUrl;
    @Column(name = "release_date")
    private Date releaseDate;
    private int duration;
    private double rate;
    private String country;
    private String director;
    private String classification;
    @Column(name = "movie_description", columnDefinition = "TEXT")
    private String movieDescription;

    @JsonIgnore
    @OneToMany(mappedBy = "movie")
    private List<Showtime> showtimes;
}
