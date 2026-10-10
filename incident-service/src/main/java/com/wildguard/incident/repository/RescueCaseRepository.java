package com.wildguard.incident.repository;

import com.wildguard.incident.entity.RescueCase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RescueCaseRepository extends JpaRepository<RescueCase, Long> {
    
    List<RescueCase> findByStatus(String status);
    List<RescueCase> findByAnimalId(Long animalId);
    List<RescueCase> findByIncidentId(Long incidentId);
    
    long countByStatus(String status);
    
    Optional<RescueCase> findByRescueCode(String rescueCode);
    
    List<RescueCase> findTop5ByOrderByCreatedAtDesc();
}
