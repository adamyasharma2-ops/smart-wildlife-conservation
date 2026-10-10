package com.wildguard.patrol.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(schema="patrol", name="patrol_resource_assignments")
public class PatrolResourceAssignment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long patrolId;
    private String resourceType;
    private Long resourceId;
    private String resourceName;
    private LocalDateTime assignedAt;
    private LocalDateTime releasedAt;

    public PatrolResourceAssignment() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getPatrolId() { return patrolId; }
    public void setPatrolId(Long patrolId) { this.patrolId = patrolId; }
    public String getResourceType() { return resourceType; }
    public void setResourceType(String resourceType) { this.resourceType = resourceType; }
    public Long getResourceId() { return resourceId; }
    public void setResourceId(Long resourceId) { this.resourceId = resourceId; }
    public String getResourceName() { return resourceName; }
    public void setResourceName(String resourceName) { this.resourceName = resourceName; }
    public LocalDateTime getAssignedAt() { return assignedAt; }
    public void setAssignedAt(LocalDateTime assignedAt) { this.assignedAt = assignedAt; }
    public LocalDateTime getReleasedAt() { return releasedAt; }
    public void setReleasedAt(LocalDateTime releasedAt) { this.releasedAt = releasedAt; }
}
