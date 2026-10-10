package com.wildguard.patrol.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(schema="patrol", name="patrol_staff_assignments")
public class PatrolStaffAssignment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long patrolId;
    private Long staffId;
    private String staffName;
    private String role;
    private LocalDateTime assignedAt;

    public PatrolStaffAssignment() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getPatrolId() { return patrolId; }
    public void setPatrolId(Long patrolId) { this.patrolId = patrolId; }
    public Long getStaffId() { return staffId; }
    public void setStaffId(Long staffId) { this.staffId = staffId; }
    public String getStaffName() { return staffName; }
    public void setStaffName(String staffName) { this.staffName = staffName; }
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
    public LocalDateTime getAssignedAt() { return assignedAt; }
    public void setAssignedAt(LocalDateTime assignedAt) { this.assignedAt = assignedAt; }
}
