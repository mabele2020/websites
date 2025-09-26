package com.example.turukiazbeach.controller;

import com.example.turukiazbeach.model.Facility;
import com.example.turukiazbeach.service.FacilityService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/facilities")
public class FacilityController {

    private final FacilityService service;
    public FacilityController(FacilityService service) { this.service = service; }

    @PostMapping
    public ResponseEntity<Facility> save(@RequestBody Facility f) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(f));
    }

    @GetMapping
    public ResponseEntity<List<Facility>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Facility> getOne(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Facility> update(@PathVariable Long id, @RequestBody Facility f) {
        return ResponseEntity.ok(service.update(id, f));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
