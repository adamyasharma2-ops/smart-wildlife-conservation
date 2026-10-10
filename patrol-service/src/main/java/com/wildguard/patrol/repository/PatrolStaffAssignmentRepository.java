package com.wildguard.patrol.repository;

import com.wildguard.patrol.entity.PatrolStaffAssignment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PatrolStaffAssignmentRepository extends JpaRepository<PatrolStaffAssignment, Long> {
    List<PatrolStaffAssignment> findByPatrolId(Long patrolId);
    List<PatrolStaffAssignment> findByStaffId(Long staffId);
}
