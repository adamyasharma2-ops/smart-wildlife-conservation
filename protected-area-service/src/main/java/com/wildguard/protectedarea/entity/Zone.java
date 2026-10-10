package com.wildguard.protectedarea.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(schema = "protected_areas", name = "zones")
public class Zone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String zoneCode;

    @Column(nullable = false)
    private Long protectedAreaId;

    private String protectedAreaName;

    @Column(nullable = false)
    private String name;

    private String zoneType;
    private Double areaKm2;
    
    @Column(columnDefinition = "TEXT")
    private String description;
    
    private Double centerLatitude;
    private Double centerLongitude;
    private String habitatType;
    private String status;
    
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Zone() {}

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
    public String getZoneCode() { return zoneCode; }
    public void setZoneCode(String zoneCode) { this.zoneCode = zoneCode; }
    public Long getProtectedAreaId() { return protectedAreaId; }
    public void setProtectedAreaId(Long protectedAreaId) { this.protectedAreaId = protectedAreaId; }
    public String getProtectedAreaName() { return protectedAreaName; }
    public void setProtectedAreaName(String protectedAreaName) { this.protectedAreaName = protectedAreaName; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getZoneType() { return zoneType; }
    public void setZoneType(String zoneType) { this.zoneType = zoneType; }
    public Double getAreaKm2() { return areaKm2; }
    public void setAreaKm2(Double areaKm2) { this.areaKm2 = areaKm2; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public Double getCenterLatitude() { return centerLatitude; }
    public void setCenterLatitude(Double centerLatitude) { this.centerLatitude = centerLatitude; }
    public Double getCenterLongitude() { return centerLongitude; }
    public void setCenterLongitude(Double centerLongitude) { this.centerLongitude = centerLongitude; }
    public String getHabitatType() { return habitatType; }
    public void setHabitatType(String habitatType) { this.habitatType = habitatType; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}
