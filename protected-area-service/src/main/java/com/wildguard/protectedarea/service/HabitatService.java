package com.wildguard.protectedarea.service;

import com.wildguard.protectedarea.entity.Habitat;
import com.wildguard.protectedarea.repository.HabitatRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class HabitatService {
    private final HabitatRepository repository;

    public HabitatService(HabitatRepository repository) {
        this.repository = repository;
    }

    public List<Habitat> findAll() { return repository.findAll(); }
    public Optional<Habitat> findById(Long id) { return repository.findById(id); }
    public Habitat save(Habitat habitat) { return repository.save(habitat); }
    public void deleteById(Long id) { repository.deleteById(id); }
    public List<Habitat> getByZone(Long zoneId) { return repository.findByZoneId(zoneId); }
    public List<Habitat> getByProtectedArea(Long paId) { return repository.findByProtectedAreaId(paId); }
}
