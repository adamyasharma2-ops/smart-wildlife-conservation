package com.wildguard.protectedarea.service;

import com.wildguard.protectedarea.entity.WaterSource;
import com.wildguard.protectedarea.repository.WaterSourceRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class WaterSourceService {
    private final WaterSourceRepository repository;

    public WaterSourceService(WaterSourceRepository repository) {
        this.repository = repository;
    }

    public List<WaterSource> findAll() { return repository.findAll(); }
    public Optional<WaterSource> findById(Long id) { return repository.findById(id); }
    public WaterSource save(WaterSource ws) { return repository.save(ws); }
    public void deleteById(Long id) { repository.deleteById(id); }
    public List<WaterSource> getByZone(Long zoneId) { return repository.findByZoneId(zoneId); }
    public List<WaterSource> getByProtectedArea(Long paId) { return repository.findByProtectedAreaId(paId); }
}
