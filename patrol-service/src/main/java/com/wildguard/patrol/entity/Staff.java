package com.wildguard.patrol.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(schema="patrol", name="staff")
public class Staff {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String staffId;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    private String designation;
    private String phone;
    private String email;
    private String team;
    private String availability;
    
    private Long assignedAreaId;
    private String assignedAreaName;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Staff() {}

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getStaffId() { return staffId; }
    public void setStaffId(String staffId) { this.staffId = staffId; }
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public String getDesignation() { return designation; }
    public void setDesignation(String designation) { this.designation = designation; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getTeam() { return team; }
    public void setTeam(String team) { this.team = team; }
    public String getAvailability() { return availability; }
    public void setAvailability(String availability) { this.availability = availability; }
    public Long getAssignedAreaId() { return assignedAreaId; }
    public void setAssignedAreaId(Long assignedAreaId) { this.assignedAreaId = assignedAreaId; }
    public String getAssignedAreaName() { return assignedAreaName; }
    public void setAssignedAreaName(String assignedAreaName) { this.assignedAreaName = assignedAreaName; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}
