package com.example.turukiazbeach.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalTime;

@Entity
@Getter
@Setter
@Table(name = "food")
public class Food {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonProperty("name")
    @Column(nullable = false)
    private String name;

    @JsonProperty("email")
    private String email;

    @JsonProperty("guest")
    private int guest;

    @JsonProperty("foodtype")
    private String foodtype;

    @JsonProperty("date")
    private LocalTime date;  // time when food is served or ordered
}
