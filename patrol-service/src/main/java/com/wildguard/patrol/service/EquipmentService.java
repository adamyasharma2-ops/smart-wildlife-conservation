package com.wildguard.patrol.service;

import com.wildguard.patrol.entity.Equipment;
import com.wildguard.patrol.repository.EquipmentRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class EquipmentService {
    private final EquipmentRepository repository;
    public EquipmentService(EquipmentRepository repository) { this.repository = repository; }
    public List<Equipment> findAll() { return repository.findAll(); }
    public Optional<Equipment> findById(Long id) { return repository.findById(id); }
    public List<Equipment> getAvailableEquipment() { return repository.findByStatus("AVAILABLE"); }
    public Equipment save(Equipment eq) { return repository.save(eq); }
    public void deleteById(Long id) { repository.deleteById(id); }
    public void updateStatus(Long id, String status) {
        repository.findById(id).ifPresent(e -> {
            e.setStatus(status);
            repository.save(e);
        });
    }
}
