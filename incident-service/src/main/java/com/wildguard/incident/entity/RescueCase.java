package com.wildguard.incident.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(schema="incidents", name="rescue_cases")
public class RescueCase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String rescueCode;

    private Long incidentId;
    private String incidentCode;
    
    private Long animalId;
    private String animalTagId;
    private String animalSpecies;

    @Column(nullable = false)
    private String rescueLocation;

    @Column(nullable = false)
    private LocalDateTime rescueDateTime;

    private String rescueTeam;

    @Column(nullable = false, length = 1000)
    private String initialCondition;
    
    @Column(length = 2000)
    private String treatmentDetails;
    
    private String rehabilitationFacility;
    
    @Column(length = 2000)
    private String rehabilitationNotes;

    private LocalDateTime transferDateTime;
    private String transferLocation;
    
    private String releaseLocation;
    private LocalDateTime releaseDateTime;
    private String outcome;

    private String status; // REPORTED, RESCUED, UNDER_TREATMENT, REHABILITATION, TRANSFERRED, RELEASED, CLOSED

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public RescueCase() {}

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getRescueCode() { return rescueCode; }
    public void setRescueCode(String rescueCode) { this.rescueCode = rescueCode; }
    public Long getIncidentId() { return incidentId; }
    public void setIncidentId(Long incidentId) { this.incidentId = incidentId; }
    public String getIncidentCode() { return incidentCode; }
    public void setIncidentCode(String incidentCode) { this.incidentCode = incidentCode; }
    public Long getAnimalId() { return animalId; }
    public void setAnimalId(Long animalId) { this.animalId = animalId; }
    public String getAnimalTagId() { return animalTagId; }
    public void setAnimalTagId(String animalTagId) { this.animalTagId = animalTagId; }
    public String getAnimalSpecies() { return animalSpecies; }
    public void setAnimalSpecies(String animalSpecies) { this.animalSpecies = animalSpecies; }
    public String getRescueLocation() { return rescueLocation; }
    public void setRescueLocation(String rescueLocation) { this.rescueLocation = rescueLocation; }
    public LocalDateTime getRescueDateTime() { return rescueDateTime; }
    public void setRescueDateTime(LocalDateTime rescueDateTime) { this.rescueDateTime = rescueDateTime; }
    public String getRescueTeam() { return rescueTeam; }
    public void setRescueTeam(String rescueTeam) { this.rescueTeam = rescueTeam; }
    public String getInitialCondition() { return initialCondition; }
    public void setInitialCondition(String initialCondition) { this.initialCondition = initialCondition; }
    public String getTreatmentDetails() { return treatmentDetails; }
    public void setTreatmentDetails(String treatmentDetails) { this.treatmentDetails = treatmentDetails; }
    public String getRehabilitationFacility() { return rehabilitationFacility; }
    public void setRehabilitationFacility(String rehabilitationFacility) { this.rehabilitationFacility = rehabilitationFacility; }
    public String getRehabilitationNotes() { return rehabilitationNotes; }
    public void setRehabilitationNotes(String rehabilitationNotes) { this.rehabilitationNotes = rehabilitationNotes; }
    public LocalDateTime getTransferDateTime() { return transferDateTime; }
    public void setTransferDateTime(LocalDateTime transferDateTime) { this.transferDateTime = transferDateTime; }
    public String getTransferLocation() { return transferLocation; }
    public void setTransferLocation(String transferLocation) { this.transferLocation = transferLocation; }
    public String getReleaseLocation() { return releaseLocation; }
    public void setReleaseLocation(String releaseLocation) { this.releaseLocation = releaseLocation; }
    public LocalDateTime getReleaseDateTime() { return releaseDateTime; }
    public void setReleaseDateTime(LocalDateTime releaseDateTime) { this.releaseDateTime = releaseDateTime; }
    public String getOutcome() { return outcome; }
    public void setOutcome(String outcome) { this.outcome = outcome; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}
