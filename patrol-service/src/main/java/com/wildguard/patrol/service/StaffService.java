package com.wildguard.patrol.service;

import com.wildguard.patrol.entity.Staff;
import com.wildguard.patrol.repository.StaffRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class StaffService {
    private final StaffRepository repository;
    public StaffService(StaffRepository repository) { this.repository = repository; }
    public List<Staff> findAll() { return repository.findAll(); }
    public Optional<Staff> findById(Long id) { return repository.findById(id); }
    public List<Staff> getAvailableStaff() { return repository.findByAvailability("AVAILABLE"); }
    public Staff save(Staff staff) { return repository.save(staff); }
    public void deleteById(Long id) { repository.deleteById(id); }
    public void updateAvailability(Long id, String status) {
        repository.findById(id).ifPresent(s -> {
            s.setAvailability(status);
            repository.save(s);
        });
    }
}
