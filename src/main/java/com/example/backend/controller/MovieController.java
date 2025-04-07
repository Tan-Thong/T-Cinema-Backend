package com.example.backend.controller;

import com.example.backend.entity.Movie;
import com.example.backend.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@RestController
public class MovieController {
    @Autowired
    private MovieService movieService;

    @GetMapping("movies")
    public ResponseEntity<List<Movie>> findAll() {
        return ResponseEntity.ok(movieService.findAll());
    }

    @GetMapping("movies/{id}")
    public ResponseEntity<Movie> findById(@PathVariable("id") int movieId) {
        return ResponseEntity.ok(movieService.findById(movieId));
    }

    @GetMapping("movies/not/{id}")
    public ResponseEntity<List<Movie>> findByIdNot(@PathVariable("id") int movieId) {
        return ResponseEntity.ok(movieService.findByIdNot(movieId));
    }

    @GetMapping("movies/upcoming")
    public ResponseEntity<List<Movie>> findUpcomingMovies() {
        return ResponseEntity.ok(movieService.findUpcomingMovies());
    }

    @GetMapping("movies/showing")
    public ResponseEntity<List<Movie>> findShowingMovies() {
        return ResponseEntity.ok(movieService.findShowingMovies());
    }

    @PostMapping(value = "/movies/add", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> createMovie(
            @RequestParam("title") String title,
            @RequestParam("releaseDate") String releaseDate,
            @RequestParam("duration") String duration,
            @RequestParam("classification") String classification,
            @RequestParam("country") String country,
            @RequestParam("rate") String rate,
            @RequestParam("director") String director,
            @RequestParam("description") String description,
            @RequestParam("trailer") String trailer,
            @RequestParam(value = "thumbnail", required = false) MultipartFile thumbnail,
            @RequestParam(value = "banner", required = false) MultipartFile banner) throws IOException {

        // Lưu file
        String fileThumnail = thumbnail.getOriginalFilename();
        Path filePathThumbnail = Paths.get("uploads/thumnails/" + fileThumnail);
        Files.createDirectories(filePathThumbnail.getParent()); // Tạo thư mục nếu chưa có
        Files.write(filePathThumbnail, thumbnail.getBytes());

        String fileBanner = banner.getOriginalFilename();
        Path filePathBanner = Paths.get("uploads/banners/" + fileBanner);
        Files.createDirectories(filePathBanner.getParent()); // Tạo thư mục nếu chưa có
        Files.write(filePathBanner, banner.getBytes());

        Movie movie = new Movie();
        movie.setTitle(title);
        movie.setCountry(country);
        movie.setDirector(director);
        movie.setRate(Double.parseDouble(rate));
        movie.setClassification(classification);
        movie.setDuration(Integer.parseInt(duration));
        movie.setTrailerUrl(trailer);
        movie.setReleaseDate(Date.valueOf(releaseDate));
        movie.setTrailerUrl(trailer);
        movie.setThumbnailUrl("uploads/thumnails/" + fileThumnail);
        movie.setBannerUrl("uploads/banners" + fileBanner);

//        System.out.println("== DỮ LIỆU NHẬN ĐƯỢC ==");
//        System.out.println("Title: " + title);
//        System.out.println("Release Date: " + releaseDate);
//        System.out.println("Duration: " + duration);
//        System.out.println("Classification: " + classification);
//        System.out.println("Country: " + country);
//        System.out.println("Rate: " + rate);
//        System.out.println("Director: " + director);
//        System.out.println("Description: " + description);
//        System.out.println("Trailer: " + trailer);
//        System.out.println("Thumbnail: " + (thumbnail != null ? thumbnail.getOriginalFilename() : "null"));
//        System.out.println("Banner: " + (banner != null ? banner.getOriginalFilename() : "null"));


        if(movieService.createMovie(movie) != null){
            // Trả về phản hồi
            return ResponseEntity.status(HttpStatus.OK).body("Tạo phim thành công!");

        }

        // Trả về phản hồi
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Tạo phim không thành công!");

    }

    @PostMapping(value = "/movies")
    public ResponseEntity<?> createMovieV2(@RequestBody Movie movie) {
        if(movieService.createMovie(movie) != null){
            return ResponseEntity.status(HttpStatus.OK).body("Tạo phim thành công!");

        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Tạo phim không thành công!");
    }
}
