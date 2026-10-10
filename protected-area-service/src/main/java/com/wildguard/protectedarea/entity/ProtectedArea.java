package com.wildguard.protectedarea.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(schema = "protected_areas", name = "protected_areas")
public class ProtectedArea {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String areaCode;

    @Column(nullable = false)
    private String name;

    private String areaType;
    private String state;
    private String country = "India";
    private Double totalAreaKm2;
    private Double latitude;
    private Double longitude;
    
    @Column(columnDefinition = "TEXT")
    private String description;
    
    private String conservationAuthority;
    private String status;
    private LocalDate establishedDate;
    
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public ProtectedArea() {}

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
    public String getAreaCode() { return areaCode; }
    public void setAreaCode(String areaCode) { this.areaCode = areaCode; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getAreaType() { return areaType; }
    public void setAreaType(String areaType) { this.areaType = areaType; }
    public String getState() { return state; }
    public void setState(String state) { this.state = state; }
    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }
    public Double getTotalAreaKm2() { return totalAreaKm2; }
    public void setTotalAreaKm2(Double totalAreaKm2) { this.totalAreaKm2 = totalAreaKm2; }
    public Double getLatitude() { return latitude; }
    public void setLatitude(Double latitude) { this.latitude = latitude; }
    public Double getLongitude() { return longitude; }
    public void setLongitude(Double longitude) { this.longitude = longitude; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getConservationAuthority() { return conservationAuthority; }
    public void setConservationAuthority(String conservationAuthority) { this.conservationAuthority = conservationAuthority; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public LocalDate getEstablishedDate() { return establishedDate; }
    public void setEstablishedDate(LocalDate establishedDate) { this.establishedDate = establishedDate; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}
