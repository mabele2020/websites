package com.example.turukiazbeach.controller;

import com.example.turukiazbeach.model.Booking;
import com.example.turukiazbeach.service.BookingService;
import com.example.turukiazbeach.service.ReceiptService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;
    private final ReceiptService receiptService;

    // ✅ CREATE
    @PostMapping
    public ResponseEntity<?> createBooking(@RequestBody Booking booking) {
        try {
            Booking saved = bookingService.createBooking(booking);
            return ResponseEntity.status(HttpStatus.CREATED).body(saved);
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Server error");
        }
    }

    // ✅ READ ALL (admin or frontend filters by user)
    @GetMapping
    public ResponseEntity<List<Booking>> getAllBookings() {
        return ResponseEntity.ok(bookingService.getAllBookings());
    }

    // ✅ READ ONE
    @GetMapping("/{id}")
    public ResponseEntity<?> getBookingById(@PathVariable Long id, @RequestHeader("X-User-Email") String userEmail) {
        Booking booking = bookingService.getBookingById(id);
        if (booking == null) return ResponseEntity.notFound().build();

        if (!booking.getEmail().equalsIgnoreCase(userEmail)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Access denied.");
        }

        return ResponseEntity.ok(booking);
    }

    // ✅ UPDATE (ownership enforced)
    @PutMapping("/{id}")
    public ResponseEntity<?> updateBooking(@PathVariable Long id,
                                           @RequestBody Booking bookingDetails,
                                           @RequestHeader("X-User-Email") String userEmail) {
        Booking existing = bookingService.getBookingById(id);
        if (existing == null) return ResponseEntity.notFound().build();

        if (!existing.getEmail().equalsIgnoreCase(userEmail)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("You can only edit your own booking.");
        }

        try {
            Booking updated = bookingService.updateBooking(id, bookingDetails);
            return ResponseEntity.ok(updated);
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // ✅ DELETE (ownership enforced)
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBooking(@PathVariable Long id,
                                           @RequestHeader("X-User-Email") String userEmail) {
        Booking existing = bookingService.getBookingById(id);
        if (existing == null) return ResponseEntity.notFound().build();

        if (!existing.getEmail().equalsIgnoreCase(userEmail)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("You can only delete your own booking.");
        }

        bookingService.deleteBooking(id);
        return ResponseEntity.noContent().build();
    }

    // ✅ DOWNLOAD RECEIPT (ownership enforced)
    @GetMapping("/{id}/receipt")
    public ResponseEntity<?> getReceipt(@PathVariable Long id,
                                        @RequestHeader("X-User-Email") String userEmail) {
        Booking booking = bookingService.getBookingById(id);
        if (booking == null) return ResponseEntity.notFound().build();

        if (!booking.getEmail().equalsIgnoreCase(userEmail)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Access denied.");
        }

        try {
            byte[] pdf = receiptService.generateReceiptPdf(booking);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDisposition(ContentDisposition.attachment()
                    .filename("booking_" + id + "_receipt.pdf").build());
            return new ResponseEntity<>(pdf, headers, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Receipt generation failed.");
        }
    }

    // ----------------- ADMIN ACTIONS -----------------

    // ✅ DELETE ANY BOOKING (admin only)
    @DeleteMapping("/admin/{id}")
    public ResponseEntity<?> adminDeleteBooking(@PathVariable Long id) {
        Booking existing = bookingService.getBookingById(id);
        if (existing == null) return ResponseEntity.notFound().build();

        bookingService.deleteBooking(id);
        return ResponseEntity.noContent().build();
    }

    // ✅ REJECT BOOKING (admin only)
    @PutMapping("/admin/{id}/reject")
    public ResponseEntity<?> rejectBooking(@PathVariable Long id) {
        Booking existing = bookingService.getBookingById(id);
        if (existing == null) return ResponseEntity.notFound().build();

        try {
            existing.setStatus("REJECTED"); // assuming Booking has a "status" field
            bookingService.updateBooking(id, existing);
            return ResponseEntity.ok(existing);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to reject booking.");
        }
    }
}
