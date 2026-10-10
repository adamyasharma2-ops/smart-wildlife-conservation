package com.wildguard.protectedarea.service;

import com.wildguard.protectedarea.entity.Zone;
import com.wildguard.protectedarea.repository.ZoneRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ZoneService {
    private final ZoneRepository repository;

    public ZoneService(ZoneRepository repository) {
        this.repository = repository;
    }

    public List<Zone> findAll() { return repository.findAll(); }
    public Optional<Zone> findById(Long id) { return repository.findById(id); }
    public Zone save(Zone zone) { return repository.save(zone); }
    public void deleteById(Long id) { repository.deleteById(id); }
    public List<Zone> getByProtectedArea(Long areaId) { return repository.findByProtectedAreaId(areaId); }
}
