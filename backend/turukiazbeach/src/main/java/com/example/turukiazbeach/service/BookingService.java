package com.example.turukiazbeach.service;

import com.example.turukiazbeach.model.Booking;

import java.util.List;

public interface BookingService {
    Booking createBooking(Booking booking);
    List<Booking> getAllBookings();
    Booking getBookingById(Long id);
    Booking updateBooking(Long id, Booking booking);
    void deleteBooking(Long id);
    boolean existsBookingConflict(String roomType, java.time.LocalDate checkin, java.time.LocalDate checkout);

}
