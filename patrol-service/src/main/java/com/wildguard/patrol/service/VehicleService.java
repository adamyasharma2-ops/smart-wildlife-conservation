package com.wildguard.patrol.service;

import com.wildguard.patrol.entity.Vehicle;
import com.wildguard.patrol.repository.VehicleRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class VehicleService {
    private final VehicleRepository repository;
    public VehicleService(VehicleRepository repository) { this.repository = repository; }
    public List<Vehicle> findAll() { return repository.findAll(); }
    public Optional<Vehicle> findById(Long id) { return repository.findById(id); }
    public List<Vehicle> getAvailableVehicles() { return repository.findByStatus("AVAILABLE"); }
    public Vehicle save(Vehicle vehicle) { return repository.save(vehicle); }
    public void deleteById(Long id) { repository.deleteById(id); }
    public void updateStatus(Long id, String status) {
        repository.findById(id).ifPresent(v -> {
            v.setStatus(status);
            repository.save(v);
        });
    }
}
