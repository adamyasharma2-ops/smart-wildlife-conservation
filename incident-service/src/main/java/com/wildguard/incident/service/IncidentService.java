package com.wildguard.incident.service;

import com.wildguard.incident.entity.Incident;
import com.wildguard.incident.repository.IncidentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class IncidentService {
    
    private final IncidentRepository repository;
    
    public IncidentService(IncidentRepository repository) {
        this.repository = repository;
    }
    
    public List<Incident> getAll() {
        return repository.findAll();
    }
    
    public Optional<Incident> getById(Long id) {
        return repository.findById(id);
    }
    
    @Transactional
    public Incident save(Incident incident) {
        if (incident.getId() == null && (incident.getIncidentCode() == null || incident.getIncidentCode().isEmpty())) {
            long count = repository.count() + 1;
            incident.setIncidentCode(String.format("INC-%03d", count));
        }
        if (incident.getStatus() == null) {
            incident.setStatus("REPORTED");
        }
        return repository.save(incident);
    }
    
    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
    }
    
    public List<Incident> searchIncidents(String zoneIdStr, String severity, String type, String status, LocalDate fromDate, LocalDate toDate) {
        List<Incident> incidents = repository.findAll();
        
        return incidents.stream()
                .filter(i -> zoneIdStr == null || zoneIdStr.isEmpty() || (i.getZoneId() != null && i.getZoneId().toString().equals(zoneIdStr)))
                .filter(i -> severity == null || severity.isEmpty() || severity.equals(i.getSeverity()))
                .filter(i -> type == null || type.isEmpty() || type.equals(i.getIncidentType()))
                .filter(i -> status == null || status.isEmpty() || status.equals(i.getStatus()))
                .filter(i -> fromDate == null || !i.getReportedAt().toLocalDate().isBefore(fromDate))
                .filter(i -> toDate == null || !i.getReportedAt().toLocalDate().isAfter(toDate))
                .collect(Collectors.toList());
    }
    
    public List<Incident> getIncidentsByZoneAndMonth(Long zoneId, int year, int month) {
        LocalDateTime start = LocalDateTime.of(year, month, 1, 0, 0);
        LocalDateTime end = start.plusMonths(1).minusNanos(1);
        if (zoneId != null) {
            return repository.findByZoneIdAndReportedAtBetween(zoneId, start, end);
        }
        return repository.findByReportedAtBetween(start, end);
    }
    
    @Transactional
    public Incident updateStatus(Long id, String status, String responseNotes) {
        Incident incident = repository.findById(id).orElseThrow(() -> new RuntimeException("Incident not found"));
        incident.setStatus(status);
        if (responseNotes != null && !responseNotes.isEmpty()) {
            String currentNotes = incident.getResponseNotes() != null ? incident.getResponseNotes() + "\n" : "";
            incident.setResponseNotes(currentNotes + LocalDateTime.now() + " - " + status + ": " + responseNotes);
        }
        return repository.save(incident);
    }
    
    @Transactional
    public Incident resolve(Long id, String resolutionDetails) {
        Incident incident = repository.findById(id).orElseThrow(() -> new RuntimeException("Incident not found"));
        incident.setStatus("RESOLVED");
        incident.setResolutionDetails(resolutionDetails);
        incident.setResolvedAt(LocalDateTime.now());
        return repository.save(incident);
    }
    
    public Map<String, Object> getDashboardStats() {
        Map<String, Object> stats = new HashMap<>();
        List<Incident> all = repository.findAll();
        
        long unresolved = all.stream().filter(i -> !List.of("RESOLVED", "CLOSED").contains(i.getStatus())).count();
        long critical = all.stream().filter(i -> "CRITICAL".equals(i.getSeverity())).count();
        
        Map<String, Long> bySeverity = all.stream().collect(Collectors.groupingBy(i -> i.getSeverity() != null ? i.getSeverity() : "UNKNOWN", Collectors.counting()));
        Map<String, Long> byType = all.stream().collect(Collectors.groupingBy(i -> i.getIncidentType() != null ? i.getIncidentType() : "UNKNOWN", Collectors.counting()));
        Map<String, Long> byStatus = all.stream().collect(Collectors.groupingBy(i -> i.getStatus() != null ? i.getStatus() : "UNKNOWN", Collectors.counting()));
        
        stats.put("unresolved", unresolved);
        stats.put("critical", critical);
        stats.put("bySeverity", bySeverity);
        stats.put("byType", byType);
        stats.put("byStatus", byStatus);
        stats.put("recentIncidents", repository.findTop10ByOrderByReportedAtDesc());
        
        return stats;
    }
}
