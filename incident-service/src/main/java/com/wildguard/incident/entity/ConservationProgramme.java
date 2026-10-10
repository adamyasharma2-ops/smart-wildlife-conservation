package com.wildguard.incident.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(schema="incidents", name="conservation_programmes")
public class ConservationProgramme {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String programmeCode;

    @Column(nullable = false)
    private String name;

    @Column(length = 2000)
    private String description;
    
    @Column(length = 2000)
    private String objectives;

    private Long targetProtectedAreaId;
    private String targetProtectedAreaName;
    
    private Long targetZoneId;
    private String targetZoneName;

    @Column(nullable = false)
    private LocalDate startDate;
    
    private LocalDate endDate;

    private String status; // PLANNED, ACTIVE, COMPLETED, SUSPENDED, CANCELLED

    private String responsiblePersonnelId;
    private String responsiblePersonnelName;

    private Integer completionPercentage = 0; // 0-100

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public ConservationProgramme() {}

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
    public String getProgrammeCode() { return programmeCode; }
    public void setProgrammeCode(String programmeCode) { this.programmeCode = programmeCode; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getObjectives() { return objectives; }
    public void setObjectives(String objectives) { this.objectives = objectives; }
    public Long getTargetProtectedAreaId() { return targetProtectedAreaId; }
    public void setTargetProtectedAreaId(Long targetProtectedAreaId) { this.targetProtectedAreaId = targetProtectedAreaId; }
    public String getTargetProtectedAreaName() { return targetProtectedAreaName; }
    public void setTargetProtectedAreaName(String targetProtectedAreaName) { this.targetProtectedAreaName = targetProtectedAreaName; }
    public Long getTargetZoneId() { return targetZoneId; }
    public void setTargetZoneId(Long targetZoneId) { this.targetZoneId = targetZoneId; }
    public String getTargetZoneName() { return targetZoneName; }
    public void setTargetZoneName(String targetZoneName) { this.targetZoneName = targetZoneName; }
    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }
    public LocalDate getEndDate() { return endDate; }
    public void setEndDate(LocalDate endDate) { this.endDate = endDate; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getResponsiblePersonnelId() { return responsiblePersonnelId; }
    public void setResponsiblePersonnelId(String responsiblePersonnelId) { this.responsiblePersonnelId = responsiblePersonnelId; }
    public String getResponsiblePersonnelName() { return responsiblePersonnelName; }
    public void setResponsiblePersonnelName(String responsiblePersonnelName) { this.responsiblePersonnelName = responsiblePersonnelName; }
    public Integer getCompletionPercentage() { return completionPercentage; }
    public void setCompletionPercentage(Integer completionPercentage) { this.completionPercentage = completionPercentage; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}
