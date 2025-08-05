package com.example.turukiazbeach.service;

import com.example.turukiazbeach.model.GalleryImage;

import java.util.List;

public interface GalleryImageService {
    GalleryImage save(GalleryImage g);
    List<GalleryImage> findAll();
    GalleryImage findById(Long id);
    GalleryImage update(Long id, GalleryImage g);
    void delete(Long id);
}
