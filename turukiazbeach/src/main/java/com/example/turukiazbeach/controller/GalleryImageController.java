package com.example.turukiazbeach.controller;

import com.example.turukiazbeach.model.GalleryImage;
import com.example.turukiazbeach.service.GalleryImageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/gallery")
public class GalleryImageController {

    private final GalleryImageService service;
    public GalleryImageController(GalleryImageService service) { this.service = service; }

    @PostMapping
    public ResponseEntity<GalleryImage> save(@RequestBody GalleryImage g) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(g));
    }

    @GetMapping
    public ResponseEntity<List<GalleryImage>> all() { return ResponseEntity.ok(service.findAll()); }

    @GetMapping("/{id}") public ResponseEntity<GalleryImage> one(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PutMapping("/{id}") public ResponseEntity<GalleryImage> update(@PathVariable Long id, @RequestBody GalleryImage g) {
        return ResponseEntity.ok(service.update(id, g));
    }

    @DeleteMapping("/{id}") public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
