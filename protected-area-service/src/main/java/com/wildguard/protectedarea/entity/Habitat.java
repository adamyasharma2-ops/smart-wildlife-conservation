package com.wildguard.protectedarea.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(schema = "protected_areas", name = "habitats")
public class Habitat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long zoneId;
    private String zoneName;
    private Long protectedAreaId;
    private String protectedAreaName;

    private String habitatType;
    private String vegetationType;
    private String dominantSpecies;
    private String condition;
    private Double coverageKm2;
    
    @Column(columnDefinition = "TEXT")
    private String notes;
    
    private LocalDate lastAssessedDate;
    
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Habitat() {}

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
    public Long getZoneId() { return zoneId; }
    public void setZoneId(Long zoneId) { this.zoneId = zoneId; }
    public String getZoneName() { return zoneName; }
    public void setZoneName(String zoneName) { this.zoneName = zoneName; }
    public Long getProtectedAreaId() { return protectedAreaId; }
    public void setProtectedAreaId(Long protectedAreaId) { this.protectedAreaId = protectedAreaId; }
    public String getProtectedAreaName() { return protectedAreaName; }
    public void setProtectedAreaName(String protectedAreaName) { this.protectedAreaName = protectedAreaName; }
    public String getHabitatType() { return habitatType; }
    public void setHabitatType(String habitatType) { this.habitatType = habitatType; }
    public String getVegetationType() { return vegetationType; }
    public void setVegetationType(String vegetationType) { this.vegetationType = vegetationType; }
    public String getDominantSpecies() { return dominantSpecies; }
    public void setDominantSpecies(String dominantSpecies) { this.dominantSpecies = dominantSpecies; }
    public String getCondition() { return condition; }
    public void setCondition(String condition) { this.condition = condition; }
    public Double getCoverageKm2() { return coverageKm2; }
    public void setCoverageKm2(Double coverageKm2) { this.coverageKm2 = coverageKm2; }
    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
    public LocalDate getLastAssessedDate() { return lastAssessedDate; }
    public void setLastAssessedDate(LocalDate lastAssessedDate) { this.lastAssessedDate = lastAssessedDate; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}
