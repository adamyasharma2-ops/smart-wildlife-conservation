package com.wildguard.wildlife.service;

import com.wildguard.wildlife.entity.Animal;
import com.wildguard.wildlife.repository.AnimalRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class AnimalService {
    private final AnimalRepository repository;
    public AnimalService(AnimalRepository repository) { this.repository = repository; }
    public List<Animal> findAll() { return repository.findAll(); }
    public Optional<Animal> findById(Long id) { return repository.findById(id); }
    public Optional<Animal> findByTagId(String tagId) { return repository.findByTagId(tagId); }
    public Animal save(Animal animal) { return repository.save(animal); }
    public void deleteById(Long id) { repository.deleteById(id); }
    public List<Animal> search(String query) { return repository.searchByTagOrNameOrSpecies(query); }
    public List<Animal> findByHealthStatus(String status) { return repository.findByHealthStatus(status); }
    public List<Animal> findBySpeciesId(Long speciesId) { return repository.findBySpeciesId(speciesId); }
    public long countByHealthStatus(String status) { return repository.countByHealthStatus(status); }
}
