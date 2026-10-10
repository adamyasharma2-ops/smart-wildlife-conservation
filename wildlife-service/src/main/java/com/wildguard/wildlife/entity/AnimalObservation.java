package com.wildguard.wildlife.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(schema = "wildlife", name = "animal_observations")
public class AnimalObservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long animalId;
    private String animalTagId;

    @Column(nullable = false)
    private String observerName;

    @Column(nullable = false)
    private LocalDate observationDate;

    private Double latitude;
    private Double longitude;
    private String location;
    private String behavior;
    private String healthNote;
    @Column(columnDefinition = "TEXT")
    private String notes;

    private LocalDateTime createdAt;

    public AnimalObservation() {}

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getAnimalId() { return animalId; }
    public void setAnimalId(Long animalId) { this.animalId = animalId; }
    public String getAnimalTagId() { return animalTagId; }
    public void setAnimalTagId(String animalTagId) { this.animalTagId = animalTagId; }
    public String getObserverName() { return observerName; }
    public void setObserverName(String observerName) { this.observerName = observerName; }
    public LocalDate getObservationDate() { return observationDate; }
    public void setObservationDate(LocalDate observationDate) { this.observationDate = observationDate; }
    public Double getLatitude() { return latitude; }
    public void setLatitude(Double latitude) { this.latitude = latitude; }
    public Double getLongitude() { return longitude; }
    public void setLongitude(Double longitude) { this.longitude = longitude; }
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
    public String getBehavior() { return behavior; }
    public void setBehavior(String behavior) { this.behavior = behavior; }
    public String getHealthNote() { return healthNote; }
    public void setHealthNote(String healthNote) { this.healthNote = healthNote; }
    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
