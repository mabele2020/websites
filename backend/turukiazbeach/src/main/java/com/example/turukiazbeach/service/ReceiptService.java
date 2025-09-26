package com.example.turukiazbeach.service;

import com.example.turukiazbeach.model.Booking;

public interface ReceiptService {
    byte[] generateReceiptPdf(Booking booking) throws Exception;
}
