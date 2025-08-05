package com.example.turukiazbeach.service;

import com.example.turukiazbeach.model.Facility;

import java.util.List;

public interface FacilityService {
    Facility save(Facility f);
    List<Facility> findAll();
    Facility findById(Long id);
    Facility update(Long id, Facility f);
    void delete(Long id);
}
