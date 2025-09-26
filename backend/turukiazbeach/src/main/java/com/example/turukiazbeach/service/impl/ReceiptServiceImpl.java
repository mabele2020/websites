package com.example.turukiazbeach.service.impl;

import com.example.turukiazbeach.model.Booking;
import com.example.turukiazbeach.service.ReceiptService;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Service
public class ReceiptServiceImpl implements ReceiptService {

    @Override
    public byte[] generateReceiptPdf(Booking booking) {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            Document document = new Document();
            PdfWriter.getInstance(document, baos);
            document.open();

            document.add(new Paragraph("Turkuaz Beach Hotel - Booking Receipt"));
            document.add(new Paragraph(" "));
            document.add(new Paragraph("Booking ID: " + booking.getId()));
            document.add(new Paragraph("Guest: " + booking.getGuestName()));
            document.add(new Paragraph("Email: " + booking.getEmail()));
            document.add(new Paragraph("Room type: " + booking.getRoomType()));
            document.add(new Paragraph("Check-in: " + booking.getCheckInDate()));
            document.add(new Paragraph("Check-out: " + booking.getCheckOutDate()));
            document.add(new Paragraph("Guests: " + booking.getGuests()));
            document.add(new Paragraph("Address: " + booking.getAddress() + ", " + booking.getCity()));
            document.add(new Paragraph("Notes: Thank you for booking with Turkuaz Beach Hotel."));

            document.close();
            return baos.toByteArray();
        } catch (DocumentException | IOException e) {
            throw new RuntimeException("Failed to generate PDF receipt", e);
        }
    }
}
