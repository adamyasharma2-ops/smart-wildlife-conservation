package com.wildguard.incident.repository;

import com.wildguard.incident.entity.ProgrammeActivity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProgrammeActivityRepository extends JpaRepository<ProgrammeActivity, Long> {
    
    List<ProgrammeActivity> findByProgrammeId(Long programmeId);
    List<ProgrammeActivity> findByStatus(String status);
    
    long countByProgrammeIdAndStatus(Long programmeId, String status);
}
