package com.example.turukiazbeach.repository;

import com.example.turukiazbeach.model.Receipt;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReceiptRepository extends JpaRepository<Receipt, Long> {
}
