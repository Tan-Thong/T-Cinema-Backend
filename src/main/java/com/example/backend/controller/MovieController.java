package com.example.backend.controller;

import com.example.backend.dto.request.MovieCreationRequest;
import com.example.backend.dto.request.MovieUpdateRequest;
import com.example.backend.dto.response.ApiResponse;
import com.example.backend.entity.Movie;
import com.example.backend.service.FileService;
import com.example.backend.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
public class MovieController {
    @Autowired
    private MovieService movieService;
    @Autowired
    private FileService fileService;

    @GetMapping("movies")
    public ResponseEntity<List<Movie>> getMovies() {
        return ResponseEntity.ok(movieService.getMovies());
    }

    @GetMapping("movies/{id}")
    public ApiResponse<Movie> getMovie(@PathVariable("id") int movieId) {
        ApiResponse<Movie> apiResponse = new ApiResponse<>();
        apiResponse.setResult(movieService.getMovie(movieId));
        return apiResponse;
    }

    @PostMapping("movies/{id}")
    public ApiResponse<Movie> updateMovie(@PathVariable("id") int movieId, @RequestBody MovieUpdateRequest request) {
        ApiResponse<Movie> apiResponse = new ApiResponse<>();
        apiResponse.setResult(movieService.updateMovie(movieId, request));
        return apiResponse;
    }

    @DeleteMapping("movies/{id}")
    public ApiResponse<String> deleteMovie(@PathVariable("id") int movieId) {
        ApiResponse<String> apiResponse = new ApiResponse<>();
        movieService.deleteMovie(movieId);
        return apiResponse;
    }

    @PostMapping(value = "/movies", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ApiResponse<Movie> createMovie(
            @RequestPart("data") MovieCreationRequest request,
            @RequestPart(value = "thumbnail", required = false) MultipartFile thumbnail,
            @RequestPart(value = "banner", required = false) MultipartFile banner
    ) {
        // Xử lý file
        String thumbnailPath = fileService.saveFile(thumbnail, "thumbnails");
        String bannerPath = fileService.saveFile(banner, "banners");

        request.setThumbnailUrl(thumbnailPath);
        request.setBannerUrl(bannerPath);
        ApiResponse<Movie> apiResponse = new ApiResponse<>();
        apiResponse.setResult(movieService.createMovie(request));
        return apiResponse;
    }


    @GetMapping("movies/not/{id}")
    public ResponseEntity<List<Movie>> getByIdNot(@PathVariable("id") int movieId) {
        return ResponseEntity.ok(movieService.findByIdNot(movieId));
    }

    @GetMapping("movies/upcoming")
    public ResponseEntity<List<Movie>> getUpcomingMovies() {
        return ResponseEntity.ok(movieService.findUpcomingMovies());
    }

    @GetMapping("movies/showing")
    public ResponseEntity<List<Movie>> getShowingMovies() {
        return ResponseEntity.ok(movieService.findShowingMovies());
    }
}
