package com.example.backend.controller;

import com.example.backend.dto.request.MovieCreationRequest;
import com.example.backend.dto.request.MovieUpdateRequest;
import com.example.backend.dto.response.ApiResponse;
import com.example.backend.dto.response.ShowtimeResponse;
import com.example.backend.entity.Movie;
import com.example.backend.service.CloudinaryFileService;
import com.example.backend.service.FileService;
import com.example.backend.service.MovieService;
import com.example.backend.service.ShowtimeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {
    @Autowired
    private MovieService movieService;

    @Autowired
    private ShowtimeService showtimeService;

    @Autowired
    private FileService fileService;

    @Autowired
    private CloudinaryFileService cloudinaryFileService;

    @GetMapping("")
    public ResponseEntity<List<Movie>> getMovies() {
        return ResponseEntity.ok(movieService.getMovies());
    }

    @GetMapping("/{id}")
    public ApiResponse<Movie> getMovie(@PathVariable("id") int movieId) {
        ApiResponse<Movie> apiResponse = new ApiResponse<>();
        apiResponse.setResult(movieService.getMovie(movieId));
        return apiResponse;
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public ApiResponse<Movie> updateMovie(
            @PathVariable("id") int movieId,
            @RequestPart("data") @Valid MovieUpdateRequest request,
            @RequestPart(value = "thumbnail", required = false) MultipartFile thumbnail,
            @RequestPart(value = "banner", required = false) MultipartFile banner) {

        String thumbnailPath = fileService.saveFile(thumbnail, "thumbnails");
        String bannerPath = fileService.saveFile(banner, "banners");

        request.setThumbnailUrl(thumbnailPath);
        request.setBannerUrl(bannerPath);

        ApiResponse<Movie> apiResponse = new ApiResponse<>();
        apiResponse.setResult(movieService.updateMovie(movieId, request));
        return apiResponse;
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public ApiResponse<String> deleteMovie(@PathVariable("id") int movieId) {
        ApiResponse<String> apiResponse = new ApiResponse<>();
        movieService.deleteMovie(movieId);
        return apiResponse;
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public ApiResponse<Movie> createMovie(
            @RequestPart("data") @Valid MovieCreationRequest request,
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


    @GetMapping("/not/{id}")
    public ResponseEntity<List<Movie>> getByIdNot(@PathVariable("id") int movieId) {
        return ResponseEntity.ok(movieService.findByIdNot(movieId));
    }

    @GetMapping("/upcoming")
    public ResponseEntity<List<Movie>> getUpcomingMovies() {
        return ResponseEntity.ok(movieService.findUpcomingMovies());
    }

    @GetMapping("/showing")
    public ResponseEntity<List<Movie>> getShowingMovies() {
        return ResponseEntity.ok(movieService.findShowingMovies());
    }

    @GetMapping("/{id}/showtimes")
    public ApiResponse<List<ShowtimeResponse>> findByMovieAndDate(@PathVariable("id") int movieId, @RequestParam String showDate) {
        ApiResponse<List<ShowtimeResponse>> apiResponse = new ApiResponse<>();
        apiResponse.setResult(showtimeService.getShowtimesByMovieAndDate(movieId, showDate));
        return apiResponse;
    }
}
