package com.example.backend.service;

import com.example.backend.dto.request.BookingRequest;
import com.example.backend.dto.response.BookingResponse;
import com.example.backend.entity.*;
import com.example.backend.enums.SeatStatus;
import com.example.backend.enums.SeatType;
import com.example.backend.mapper.BookingMapper;
import com.example.backend.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class BookingService {
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private ShowtimeRepository showtimeRepository;
    @Autowired
    private SeatRepository seatRepository;
    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    private SeatShowtimeRepository seatShowtimeRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BookingMapper bookingMapper;

    @Transactional
    public Booking createBooking(BookingRequest bookingRequest) {
        if (bookingRequest.getSeatIds() == null || bookingRequest.getSeatIds().isEmpty()) {
            throw new RuntimeException("Bạn phải chọn ít nhất một ghế để đặt vé.");
        }

        Booking booking = new Booking();
        booking.setBookingDate(LocalDate.now());
        booking.setUser(userRepository.findByEmail(bookingRequest.getUserId()).orElseThrow(()->new RuntimeException("User không tồn tại!")));
        booking.setTotalPrice(bookingRequest.getTotalPrice());
        bookingRepository.save(bookingRepository.save(booking));
        List<Ticket> tickets = new ArrayList<>();
        for(int i = 0; i < bookingRequest.getSeatIds().size(); i++) {
            Ticket ticket = new Ticket();
            ticket.setBooking(booking);
            Seat seat = seatRepository.findById(bookingRequest.getSeatIds().get(i)).orElseThrow(()->new RuntimeException("Ghế không tồn tại!"));
            ticket.setSeat(seat);
            ticket.setTicketPrice(seat.getSeatType() == SeatType.STANDARD ? 80000.0 : 100000.0 );
            Showtime showtime = showtimeRepository.findById(bookingRequest.getShowtimeId()).orElseThrow(()->new RuntimeException("Suất chiếu không tồn tại!"));
            ticket.setShowtime(showtime);
            ticketRepository.save(ticket);
            tickets.add(ticket);
            SeatShowtime seatShowtime = seatShowtimeRepository.findById(new SeatShowtimeId(seat.getSeatId(), showtime.getShowtimeId())).orElseThrow(()->new RuntimeException("Ghế của suất chiếu không tồn tại!"));
            seatShowtime.setSeatStatus(SeatStatus.BOOKED);
            seatShowtimeRepository.save(seatShowtime);
        }
        booking.setTickets(tickets);
        return bookingRepository.save(booking);
    }

    public List<BookingResponse> getBookings() {
        return bookingMapper.toBookingsResponse(bookingRepository.findAll());
    }
}
