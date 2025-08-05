package com.example.turukiazbeach.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Room {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String description;
    private double pricePerNight;
    private int capacity;
    private String imageUrl;

    // Getters, setters, constructors
}
