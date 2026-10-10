package com.wildguard.incident.repository;

import com.wildguard.incident.entity.Incident;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface IncidentRepository extends JpaRepository<Incident, Long> {
    
    List<Incident> findByStatus(String status);
    List<Incident> findByZoneId(Long zoneId);
    List<Incident> findByProtectedAreaId(Long protectedAreaId);
    List<Incident> findBySeverity(String severity);
    List<Incident> findByIncidentType(String incidentType);
    
    List<Incident> findByReportedAtBetween(LocalDateTime start, LocalDateTime end);
    
    List<Incident> findByZoneIdAndReportedAtBetween(Long zoneId, LocalDateTime start, LocalDateTime end);
    
    long countByStatus(String status);
    long countBySeverity(String severity);
    
    List<Incident> findTop10ByOrderByReportedAtDesc();
    
    List<Incident> findByStatusIn(List<String> statuses);
    
    Optional<Incident> findByIncidentCode(String incidentCode);
    
    @Query("SELECT i FROM Incident i WHERE LOWER(i.description) LIKE LOWER(CONCAT('%', :keyword, '%')) OR LOWER(i.incidentCode) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Incident> searchByKeyword(@Param("keyword") String keyword);
}
