package com.example.turukiazbeach.service.impl;

import com.example.turukiazbeach.model.GalleryImage;
import com.example.turukiazbeach.repository.GalleryImageRepository;
import com.example.turukiazbeach.service.GalleryImageService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class GalleryImageServiceImpl implements GalleryImageService {

    private final GalleryImageRepository repo;

    public GalleryImageServiceImpl(GalleryImageRepository repo) {
        this.repo = repo;
    }

    @Override
    public GalleryImage save(GalleryImage image) {
        return repo.save(image);
    }

    @Override
    public List<GalleryImage> findAll() {
        return repo.findAll();
    }

    @Override
    public GalleryImage findById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Gallery image with ID " + id + " not found"));
    }

    @Override
    public GalleryImage update(Long id, GalleryImage g) {
        GalleryImage existing = findById(id);

        existing.setImageUrl(g.getImageUrl());

        existing.setCaption(g.getCaption());

        return repo.save(existing);
    }

    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }
}
