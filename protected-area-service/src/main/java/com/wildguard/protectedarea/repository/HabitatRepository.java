package com.wildguard.protectedarea.repository;

import com.wildguard.protectedarea.entity.Habitat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface HabitatRepository extends JpaRepository<Habitat, Long> {
    List<Habitat> findByZoneId(Long zoneId);
    List<Habitat> findByProtectedAreaId(Long protectedAreaId);
    List<Habitat> findByCondition(String condition);
    List<Habitat> findByHabitatType(String habitatType);
}
