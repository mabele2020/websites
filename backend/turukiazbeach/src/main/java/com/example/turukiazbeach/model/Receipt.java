package com.example.turukiazbeach.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Receipt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long bookingId;

    private String guestName;
    private String email;
    private String roomType;
    private LocalDateTime generatedAt;

    @Lob
    private byte[] pdfContent;
}
