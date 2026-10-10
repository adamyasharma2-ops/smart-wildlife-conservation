package com.wildguard.patrol.repository;

import com.wildguard.patrol.entity.PatrolResourceAssignment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface PatrolResourceAssignmentRepository extends JpaRepository<PatrolResourceAssignment, Long> {
    List<PatrolResourceAssignment> findByPatrolId(Long patrolId);
    Optional<PatrolResourceAssignment> findByResourceTypeAndResourceId(String resourceType, Long resourceId);
    List<PatrolResourceAssignment> findByPatrolIdAndReleasedAtIsNull(Long patrolId);
}
