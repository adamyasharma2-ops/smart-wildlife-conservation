package com.wildguard.protectedarea.repository;

import com.wildguard.protectedarea.entity.Zone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface ZoneRepository extends JpaRepository<Zone, Long> {
    List<Zone> findByProtectedAreaId(Long protectedAreaId);
    Optional<Zone> findByZoneCode(String zoneCode);
    List<Zone> findByStatus(String status);
    long countByProtectedAreaId(Long protectedAreaId);
}
