package com.sagargarrie.service.impl;

import com.sagargarrie.dto.BookingRequestDTO;
import com.sagargarrie.dto.BookingResponseDTO;
import com.sagargarrie.entity.Booking;
import com.sagargarrie.repository.BookingRepository;
import com.sagargarrie.service.BookingService;
import jakarta.transaction.Transactional;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;

    @Value("${restaurant.max_total_seats:50}")
    private Integer maxTotalSeats;

    public BookingServiceImpl(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    @Override
    @Transactional
    public BookingResponseDTO createBooking(BookingRequestDTO req) throws BadRequestException {
        LocalDate date = req.getBookingDate();
        LocalTime time = req.getBookingTime();
        Integer existing = bookingRepository.sumPaxForDateTime(date, time);
        if (existing == null) existing = 0;
        if (existing + req.getPax() > maxTotalSeats) {
            throw new BadRequestException("Not enough seats available for this slot");
        }

        Booking b = new Booking();
        b.setCustomerName(req.getCustomerName());
        b.setPhone(req.getPhone());
        b.setBookingDate(req.getBookingDate());
        b.setBookingTime(req.getBookingTime());
        b.setPax(req.getPax());
        b.setNotes(req.getNotes());
        b.setPayNow(req.getPayNow() != null ? req.getPayNow() : false);
        b.setStatus("pending");

        Booking saved = bookingRepository.save(b);
        return mapToDto(saved);
    }

    @Override
    public List<BookingResponseDTO> listBookings(LocalDate date) {
        List<Booking> bookings = bookingRepository.findByBookingDate(date);
        return bookings.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    private BookingResponseDTO mapToDto(Booking b) {
        BookingResponseDTO d = new BookingResponseDTO();
        d.setId(b.getId());
        d.setCustomerName(b.getCustomerName());
        d.setPhone(b.getPhone());
        d.setBookingDate(b.getBookingDate());
        d.setBookingTime(b.getBookingTime());
        d.setPax(b.getPax());
        d.setStatus(b.getStatus());
        return d;
    }

}
