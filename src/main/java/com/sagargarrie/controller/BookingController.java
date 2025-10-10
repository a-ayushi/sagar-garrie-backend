package com.sagargarrie.controller;

import com.sagargarrie.dto.ApiResponse;
import com.sagargarrie.dto.BookingRequestDTO;
import com.sagargarrie.dto.BookingResponseDTO;
import com.sagargarrie.repository.BookingRepository;
import com.sagargarrie.service.BookingService;
import jakarta.validation.Valid;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/bookings")
@CrossOrigin(origins = "http://localhost:5173")
public class BookingController {

    private final BookingService bookingService;
    public BookingController(BookingService bookingService) { this.bookingService = bookingService; }

    @Autowired
    private BookingRepository bookingRepository;

    @PostMapping
    public ResponseEntity<ApiResponse<BookingResponseDTO>> create(@Valid @RequestBody BookingRequestDTO req) {
        try {
            BookingResponseDTO resp = bookingService.createBooking(req);
            ApiResponse<BookingResponseDTO> apiResp = new ApiResponse<>(true, resp, "Booking created successfully");
            return ResponseEntity.ok(apiResp);
        } catch (BadRequestException ex) {
            ApiResponse<BookingResponseDTO> apiResp = new ApiResponse<>(false, null, ex.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResp);
        } catch (Exception ex) {
            // log exception on server side if needed
            ApiResponse<BookingResponseDTO> apiResp = new ApiResponse<>(false, null, "Internal server error");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiResp);
        }
    }
    @GetMapping
    public ResponseEntity<
            List<BookingResponseDTO>> list(
            @RequestParam(value="date", required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        if (date == null) date = LocalDate.now();
        List<BookingResponseDTO> list = bookingService.listBookings(date);
        return ResponseEntity.ok(list);
    }
}
