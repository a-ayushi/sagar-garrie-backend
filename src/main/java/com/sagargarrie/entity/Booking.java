package com.sagargarrie.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "bookings")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="customer_name", nullable=false)
    private String customerName;

    @Column(nullable=false)
    private String phone;

    @Column(name="booking_date", nullable=false)
    private LocalDate bookingDate;

    @Column(name="booking_time", nullable=false)
    private LocalTime bookingTime;

    private Integer pax;

    @Column(columnDefinition = "text")
    private String notes;

    private String status = "pending";

    @Column(name="pay_now")
    private Boolean payNow = false;

    @Column(name="payment_id")
    private Long paymentId;

    @Column(name="created_at")
    private OffsetDateTime createdAt;

    @PrePersist
    public void prePersist() { createdAt = OffsetDateTime.now(); }
}
