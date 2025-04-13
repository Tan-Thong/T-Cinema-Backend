package com.example.backend.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.sql.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MovieUpdateRequest {
    String title;
    String trailerUrl;
    String thumbnailUrl;
    String bannerUrl;
    Date releaseDate;
    int duration;
    double rate;
    String country;
    String director;
    String classification;
    String movieDescription;
}
