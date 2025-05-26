package com.example.backend.controller;

import com.example.backend.dto.request.BookingRequest;
import com.example.backend.dto.response.ApiResponse;
import com.example.backend.dto.response.BookingResponse;
import com.example.backend.entity.*;
import com.example.backend.repository.*;
import com.example.backend.service.BookingService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping
    public ApiResponse<Booking> createBooking(@RequestBody BookingRequest bookingRequest) {
        ApiResponse<Booking> apiResponse = new ApiResponse<>();
        apiResponse.setResult(bookingService.createBooking(bookingRequest));
        return apiResponse;
    }

    @GetMapping
    public ApiResponse<List<BookingResponse>> getBookings() {
        ApiResponse<List<BookingResponse>> apiResponse = new ApiResponse<>();
        apiResponse.setResult(bookingService.getBookings());
        return apiResponse;
    }
}
