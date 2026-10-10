package com.wildguard.incident.service;

import com.wildguard.incident.entity.RescueCase;
import com.wildguard.incident.repository.RescueCaseRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class RescueCaseService {
    
    private final RescueCaseRepository repository;
    
    public RescueCaseService(RescueCaseRepository repository) {
        this.repository = repository;
    }
    
    public List<RescueCase> getAll() {
        return repository.findAll();
    }
    
    public Optional<RescueCase> getById(Long id) {
        return repository.findById(id);
    }
    
    @Transactional
    public RescueCase save(RescueCase rescueCase) {
        if (rescueCase.getId() == null && (rescueCase.getRescueCode() == null || rescueCase.getRescueCode().isEmpty())) {
            long count = repository.count() + 1;
            rescueCase.setRescueCode(String.format("RC-%03d", count));
        }
        if (rescueCase.getStatus() == null) {
            rescueCase.setStatus("REPORTED");
        }
        return repository.save(rescueCase);
    }
    
    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
    }
    
    @Transactional
    public RescueCase updateStatus(Long id, String status) {
        RescueCase rescueCase = repository.findById(id).orElseThrow(() -> new RuntimeException("Rescue Case not found"));
        rescueCase.setStatus(status);
        return repository.save(rescueCase);
    }
    
    @Transactional
    public RescueCase addTreatmentUpdate(Long id, String treatmentDetails) {
        RescueCase rescueCase = repository.findById(id).orElseThrow(() -> new RuntimeException("Rescue Case not found"));
        String currentDetails = rescueCase.getTreatmentDetails() != null ? rescueCase.getTreatmentDetails() + "\n" : "";
        rescueCase.setTreatmentDetails(currentDetails + LocalDateTime.now() + ": " + treatmentDetails);
        if ("REPORTED".equals(rescueCase.getStatus()) || "RESCUED".equals(rescueCase.getStatus())) {
            rescueCase.setStatus("UNDER_TREATMENT");
        }
        return repository.save(rescueCase);
    }
    
    @Transactional
    public RescueCase recordTransfer(Long id, String location, LocalDateTime dt) {
        RescueCase rescueCase = repository.findById(id).orElseThrow(() -> new RuntimeException("Rescue Case not found"));
        rescueCase.setTransferLocation(location);
        rescueCase.setTransferDateTime(dt != null ? dt : LocalDateTime.now());
        rescueCase.setStatus("TRANSFERRED");
        return repository.save(rescueCase);
    }
    
    @Transactional
    public RescueCase recordRelease(Long id, String location, LocalDateTime dt, String outcome) {
        RescueCase rescueCase = repository.findById(id).orElseThrow(() -> new RuntimeException("Rescue Case not found"));
        rescueCase.setReleaseLocation(location);
        rescueCase.setReleaseDateTime(dt != null ? dt : LocalDateTime.now());
        rescueCase.setOutcome(outcome);
        rescueCase.setStatus("RELEASED");
        return repository.save(rescueCase);
    }
    
    public List<RescueCase> getActive() {
        List<RescueCase> all = repository.findAll();
        return all.stream().filter(r -> !List.of("RELEASED", "CLOSED").contains(r.getStatus())).toList();
    }
    
    public List<RescueCase> getByAnimal(Long animalId) {
        return repository.findByAnimalId(animalId);
    }
}
