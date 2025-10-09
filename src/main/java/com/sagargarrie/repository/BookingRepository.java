package com.sagargarrie.repository;

import com.sagargarrie.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByBookingDate(LocalDate date);

    @Query("SELECT COALESCE(SUM(b.pax), 0) FROM Booking b WHERE b.bookingDate = :date AND b.bookingTime = :time")
    Integer sumPaxForDateTime(@Param("date") LocalDate date, @Param("time") LocalTime time);
}
