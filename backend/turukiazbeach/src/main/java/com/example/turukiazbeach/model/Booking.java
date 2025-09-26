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

    @JsonProperty("name")
    private String guestName;

    @JsonProperty("email")
    private String email;

    @JsonProperty("phone")           // ✅ NEW: phone field
    private String phone;

    @JsonProperty("room_id")         // ✅ NEW: room_id field
    private Integer roomId;

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

    @JsonProperty("checkinDate")
    private LocalDate checkInDate;

    @JsonProperty("checkoutDate")
    private LocalDate checkOutDate;

    @JsonProperty("roomType")
    private String roomType;

    @JsonProperty("guests")
    private int guests;

    public void setStatus(String rejected) {
    }
}
