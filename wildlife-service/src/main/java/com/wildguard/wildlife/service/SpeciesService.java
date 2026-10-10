package com.wildguard.wildlife.service;

import com.wildguard.wildlife.entity.Species;
import com.wildguard.wildlife.repository.SpeciesRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class SpeciesService {
    private final SpeciesRepository repository;
    public SpeciesService(SpeciesRepository repository) { this.repository = repository; }
    public List<Species> findAll() { return repository.findAll(); }
    public Optional<Species> findById(Long id) { return repository.findById(id); }
    public Species save(Species species) { return repository.save(species); }
    public void deleteById(Long id) { repository.deleteById(id); }
    public List<Species> search(String q) { return repository.findByCommonNameContainingIgnoreCase(q); }
}
