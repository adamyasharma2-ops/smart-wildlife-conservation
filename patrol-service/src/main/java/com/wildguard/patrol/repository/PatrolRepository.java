package com.wildguard.patrol.repository;

import com.wildguard.patrol.entity.Patrol;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface PatrolRepository extends JpaRepository<Patrol, Long> {
    List<Patrol> findByStatus(String status);
    List<Patrol> findByZoneId(Long zoneId);
    List<Patrol> findByProtectedAreaId(Long protectedAreaId);
    List<Patrol> findByPlannedStartTimeBetween(LocalDateTime start, LocalDateTime end);
    List<Patrol> findByStatusIn(List<String> statuses);
    long countByStatus(String status);
    Optional<Patrol> findByPatrolId(String patrolId);
    List<Patrol> findTop10ByOrderByCreatedAtDesc();
}
