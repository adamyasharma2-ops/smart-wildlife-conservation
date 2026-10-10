package com.wildguard.wildlife.service;

import com.wildguard.wildlife.entity.AnimalObservation;
import com.wildguard.wildlife.repository.AnimalObservationRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AnimalObservationService {
    private final AnimalObservationRepository repository;
    public AnimalObservationService(AnimalObservationRepository repository) { this.repository = repository; }
    public List<AnimalObservation> getObservationsForAnimal(Long animalId) {
        return repository.findByAnimalIdOrderByObservationDateDesc(animalId);
    }
    public AnimalObservation save(AnimalObservation obs) { return repository.save(obs); }
    public List<AnimalObservation> getRecentObservations() { return repository.findTop10ByOrderByObservationDateDesc(); }
}
