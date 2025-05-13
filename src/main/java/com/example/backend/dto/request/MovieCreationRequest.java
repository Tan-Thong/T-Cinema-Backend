package com.example.backend.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MovieCreationRequest {
    @NotBlank(message = "Tên phim không được bỏ trống!")
    String title;
    String trailerUrl;
    String thumbnailUrl;
    String bannerUrl;
    LocalDate releaseDate;
    int duration;
    double rate;
    String country;
    String director;
    String classification;
    String movieDescription;
}
