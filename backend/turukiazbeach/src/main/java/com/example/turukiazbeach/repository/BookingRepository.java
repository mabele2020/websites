package com.example.turukiazbeach.repository;

import com.example.turukiazbeach.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;

public interface BookingRepository extends JpaRepository<Booking, Long> {


    /**
     * Returns true if there exists a booking for the same roomType that overlaps the requested dates.
     * Overlap condition: existing.checkInDate < requestedCheckOut AND existing.checkOutDate > requestedCheckIn
     */
    @Query("SELECT CASE WHEN COUNT(b) > 0 THEN true ELSE false END FROM Booking b " +
            "WHERE b.roomType = :roomType " +
            "AND b.checkInDate < :checkoutDate " +
            "AND b.checkOutDate > :checkinDate")
    boolean existsBookingConflict(@Param("roomType") String roomType,
                                  @Param("checkinDate") LocalDate checkinDate,
                                  @Param("checkoutDate") LocalDate checkoutDate);
}
