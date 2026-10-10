package com.wildguard.protectedarea.repository;

import com.wildguard.protectedarea.entity.WaterSource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface WaterSourceRepository extends JpaRepository<WaterSource, Long> {
    List<WaterSource> findByZoneId(Long zoneId);
    List<WaterSource> findByProtectedAreaId(Long protectedAreaId);
    List<WaterSource> findByCondition(String condition);
    List<WaterSource> findBySourceType(String sourceType);
}
