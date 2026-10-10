package com.wildguard.incident.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(schema="incidents", name="incidents")
public class Incident {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String incidentCode;

    private String incidentType; // WILDLIFE_INJURY, HUMAN_WILDLIFE_CONFLICT, POACHING, ILLEGAL_ACTIVITY, HABITAT_DESTRUCTION, WATER_RESOURCE_THREAT, FIRE, DISEASE_OUTBREAK, OTHER

    @Column(nullable = false, length = 1000)
    private String description;

    private String severity; // LOW, MEDIUM, HIGH, CRITICAL

    @Column(nullable = false)
    private LocalDateTime reportedAt;

    private Long protectedAreaId;
    private String protectedAreaName;
    private Long zoneId;
    private String zoneName;

    private Long animalId;
    private String animalTagId;

    private Double latitude;
    private Double longitude;
    
    @Column(length = 500)
    private String locationDescription;

    private String assignedPersonnelId;
    private String assignedPersonnelName;

    private String status; // REPORTED, UNDER_INVESTIGATION, IN_PROGRESS, RESOLVED, CLOSED
    
    @Column(length = 2000)
    private String responseNotes;
    
    @Column(length = 2000)
    private String resolutionDetails;
    
    private LocalDateTime resolvedAt;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Incident() {}

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
        if (reportedAt == null) {
            reportedAt = LocalDateTime.now();
        }
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getIncidentCode() { return incidentCode; }
    public void setIncidentCode(String incidentCode) { this.incidentCode = incidentCode; }
    public String getIncidentType() { return incidentType; }
    public void setIncidentType(String incidentType) { this.incidentType = incidentType; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getSeverity() { return severity; }
    public void setSeverity(String severity) { this.severity = severity; }
    public LocalDateTime getReportedAt() { return reportedAt; }
    public void setReportedAt(LocalDateTime reportedAt) { this.reportedAt = reportedAt; }
    public Long getProtectedAreaId() { return protectedAreaId; }
    public void setProtectedAreaId(Long protectedAreaId) { this.protectedAreaId = protectedAreaId; }
    public String getProtectedAreaName() { return protectedAreaName; }
    public void setProtectedAreaName(String protectedAreaName) { this.protectedAreaName = protectedAreaName; }
    public Long getZoneId() { return zoneId; }
    public void setZoneId(Long zoneId) { this.zoneId = zoneId; }
    public String getZoneName() { return zoneName; }
    public void setZoneName(String zoneName) { this.zoneName = zoneName; }
    public Long getAnimalId() { return animalId; }
    public void setAnimalId(Long animalId) { this.animalId = animalId; }
    public String getAnimalTagId() { return animalTagId; }
    public void setAnimalTagId(String animalTagId) { this.animalTagId = animalTagId; }
    public Double getLatitude() { return latitude; }
    public void setLatitude(Double latitude) { this.latitude = latitude; }
    public Double getLongitude() { return longitude; }
    public void setLongitude(Double longitude) { this.longitude = longitude; }
    public String getLocationDescription() { return locationDescription; }
    public void setLocationDescription(String locationDescription) { this.locationDescription = locationDescription; }
    public String getAssignedPersonnelId() { return assignedPersonnelId; }
    public void setAssignedPersonnelId(String assignedPersonnelId) { this.assignedPersonnelId = assignedPersonnelId; }
    public String getAssignedPersonnelName() { return assignedPersonnelName; }
    public void setAssignedPersonnelName(String assignedPersonnelName) { this.assignedPersonnelName = assignedPersonnelName; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getResponseNotes() { return responseNotes; }
    public void setResponseNotes(String responseNotes) { this.responseNotes = responseNotes; }
    public String getResolutionDetails() { return resolutionDetails; }
    public void setResolutionDetails(String resolutionDetails) { this.resolutionDetails = resolutionDetails; }
    public LocalDateTime getResolvedAt() { return resolvedAt; }
    public void setResolvedAt(LocalDateTime resolvedAt) { this.resolvedAt = resolvedAt; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}
