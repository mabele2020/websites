package com.example.turukiazbeach.service.impl;

import com.example.turukiazbeach.model.Booking;
import com.example.turukiazbeach.repository.BookingRepository;
import com.example.turukiazbeach.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;

    @Override
    public Booking createBooking(Booking booking) {
        // Basic date validation
        LocalDate checkin = booking.getCheckInDate();
        LocalDate checkout = booking.getCheckOutDate();
        if (checkin == null || checkout == null || !checkout.isAfter(checkin)) {
            throw new IllegalArgumentException("Invalid check-in/check-out dates");
        }

        // Prevent overlapping booking for same roomType
        boolean conflict = bookingRepository.existsBookingConflict(booking.getRoomType(), checkin, checkout);
        if (conflict) {
            throw new IllegalStateException("Room already booked for the selected dates");
        }

        return bookingRepository.save(booking);
    }

    @Override
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    @Override
    public Booking getBookingById(Long id) {
        return bookingRepository.findById(id).orElse(null);
    }

    @Override
    public Booking updateBooking(Long id, Booking booking) {
        Optional<Booking> opt = bookingRepository.findById(id);
        if (opt.isEmpty()) return null;

        Booking existing = opt.get();

        // If dates/roomType changed, check for conflicts (exclude current booking id)
        LocalDate newCheckin = booking.getCheckInDate();
        LocalDate newCheckout = booking.getCheckOutDate();
        String newRoomType = booking.getRoomType();

        if (newCheckin != null && newCheckout != null && newCheckout.isAfter(newCheckin)) {
            boolean conflict = bookingRepository.existsBookingConflict(newRoomType, newCheckin, newCheckout);
            if (conflict) {
                List<Booking> overlaps = bookingRepository.findAll().stream()
                        .filter(b -> b.getRoomType().equals(newRoomType))
                        .filter(b -> b.getId() != null && !b.getId().equals(id))
                        .filter(b -> b.getCheckInDate().isBefore(newCheckout) && b.getCheckOutDate().isAfter(newCheckin))
                        .toList();
                if (!overlaps.isEmpty()) {
                    throw new IllegalStateException("Room already booked for the selected dates");
                }
            }
            existing.setCheckInDate(newCheckin);
            existing.setCheckOutDate(newCheckout);
        }

        // Update other fields
        existing.setGuestName(booking.getGuestName());
        existing.setEmail(booking.getEmail());
        existing.setGuests(booking.getGuests());
        existing.setRoomType(booking.getRoomType());
        existing.setAddress(booking.getAddress());
        existing.setCity(booking.getCity());
        existing.setState(booking.getState());
        existing.setZip(booking.getZip());

        return bookingRepository.save(existing);
    }

    @Override
    public void deleteBooking(Long id) {
        bookingRepository.deleteById(id);
    }

    @Override
    public boolean existsBookingConflict(String roomType, LocalDate checkin, LocalDate checkout) {
        return bookingRepository.existsBookingConflict(roomType, checkin, checkout);
    }
}
