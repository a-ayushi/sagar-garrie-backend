package com.sagargarrie.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class BookingRequestDTO {
    @NotBlank
    private String customerName;

    @NotBlank
    @Pattern(regexp="^[0-9]{6,15}$", message="Invalid phone number")
    private String phone;

    @NotNull
    private LocalDate bookingDate;
    @NotNull private LocalTime bookingTime;

    @NotNull @Min(1) private Integer pax;
    private String notes;
    private Boolean payNow = false;

}
