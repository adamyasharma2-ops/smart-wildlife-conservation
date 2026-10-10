package com.wildguard.protectedarea.service;

import com.wildguard.protectedarea.entity.ProtectedArea;
import com.wildguard.protectedarea.repository.ProtectedAreaRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProtectedAreaService {
    private final ProtectedAreaRepository repository;

    public ProtectedAreaService(ProtectedAreaRepository repository) {
        this.repository = repository;
    }

    public List<ProtectedArea> findAll() { return repository.findAll(); }
    public Optional<ProtectedArea> findById(Long id) { return repository.findById(id); }
    public ProtectedArea save(ProtectedArea pa) { return repository.save(pa); }
    public void deleteById(Long id) { repository.deleteById(id); }
    public List<ProtectedArea> findByStatus(String status) { return repository.findByStatus(status); }
    public List<ProtectedArea> findByState(String state) { return repository.findByState(state); }
    public List<ProtectedArea> search(String query) { return repository.findByNameContainingIgnoreCase(query); }
}
