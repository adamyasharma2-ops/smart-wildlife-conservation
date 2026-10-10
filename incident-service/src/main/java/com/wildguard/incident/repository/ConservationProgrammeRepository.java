package com.wildguard.incident.repository;

import com.wildguard.incident.entity.ConservationProgramme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ConservationProgrammeRepository extends JpaRepository<ConservationProgramme, Long> {
    
    List<ConservationProgramme> findByStatus(String status);
    List<ConservationProgramme> findByTargetProtectedAreaId(Long protectedAreaId);
    
    Optional<ConservationProgramme> findByProgrammeCode(String programmeCode);
    
    long countByStatus(String status);
}
