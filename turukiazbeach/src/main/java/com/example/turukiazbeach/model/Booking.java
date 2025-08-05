package com.example.turukiazbeach.model;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonProperty("name")          // ✅ Map "name" from frontend to guestName
    private String guestName;

    @JsonProperty("email")
    private String email;

    @JsonProperty("password")
    private String password;

    @JsonProperty("address")
    private String address;

    @JsonProperty("city")
    private String city;

    @JsonProperty("state")
    private String state;

    @JsonProperty("zip")
    private String zip;

    @JsonProperty("checkinDate")   // ✅ Map frontend checkinDate
    private LocalDate checkInDate;

    @JsonProperty("checkoutDate")  // ✅ Map frontend checkoutDate
    private LocalDate checkOutDate;

    @JsonProperty("roomType")
    private String roomType;

    @JsonProperty("guests")
    private int guests;
}
