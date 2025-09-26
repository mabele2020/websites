package com.example.turukiazbeach.controller;

import com.example.turukiazbeach.model.Receipt;
import com.example.turukiazbeach.repository.ReceiptRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/receipts")
@RequiredArgsConstructor
public class ReceiptController {

    private final ReceiptRepository receiptRepository;

    @GetMapping
    public List<Receipt> getAllReceipts() {
        return receiptRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<byte[]> getReceiptPdf(@PathVariable Long id) {
        Receipt receipt = receiptRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Receipt not found"));

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=receipt_" + id + ".pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(receipt.getPdfContent());
    }

    @PostMapping
    public Receipt saveReceipt(@RequestBody Receipt receipt) {
        return receiptRepository.save(receipt);
    }

    @DeleteMapping("/{id}")
    public void deleteReceipt(@PathVariable Long id) {
        receiptRepository.deleteById(id);
    }
}
