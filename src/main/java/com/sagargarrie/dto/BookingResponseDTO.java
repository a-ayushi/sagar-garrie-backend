package com.sagargarrie.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class BookingResponseDTO {
    private Long id;
    private String customerName;
    private String phone;
    private LocalDate bookingDate;
    private LocalTime bookingTime;
    private Integer pax;
    private String status;
}
