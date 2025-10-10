package com.sagargarrie.service;

import com.sagargarrie.dto.BookingRequestDTO;
import com.sagargarrie.dto.BookingResponseDTO;
import org.apache.coyote.BadRequestException;

import java.time.LocalDate;
import java.util.List;

public interface BookingService {
    BookingResponseDTO createBooking(BookingRequestDTO request) throws BadRequestException;
    List<BookingResponseDTO> listBookings(LocalDate date);
}
