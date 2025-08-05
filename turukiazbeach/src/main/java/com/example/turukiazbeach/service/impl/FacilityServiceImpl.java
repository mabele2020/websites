package com.example.turukiazbeach.service.impl;

import com.example.turukiazbeach.model.Facility;
import com.example.turukiazbeach.repository.FacilityRepository;
import com.example.turukiazbeach.service.FacilityService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class FacilityServiceImpl implements FacilityService {

    private final FacilityRepository repo;

    @Override
    public Facility save(Facility f) {
        return repo.save(f);
    }

    @Override
    public List<Facility> findAll() {
        return repo.findAll();
    }

    @Override
    public Facility findById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Facility not found with id " + id));
    }

    @Override
    public Facility update(Long id, Facility f) {
        Facility existing = findById(id);

        if (f.getName() != null) existing.setName(f.getName());
        if (f.getDescription() != null) existing.setDescription(f.getDescription());
        if (f.getIconUrl() != null) existing.setIconUrl(f.getIconUrl());

        return repo.save(existing);
    }

    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }
}
