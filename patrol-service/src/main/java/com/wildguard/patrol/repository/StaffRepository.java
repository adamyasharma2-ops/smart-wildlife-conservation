package com.wildguard.patrol.repository;

import com.wildguard.patrol.entity.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface StaffRepository extends JpaRepository<Staff, Long> {
    Optional<Staff> findByStaffId(String staffId);
    List<Staff> findByAvailability(String availability);
    List<Staff> findByDesignation(String designation);
    List<Staff> findByTeam(String team);
    List<Staff> findByAssignedAreaId(Long assignedAreaId);
    long countByAvailability(String availability);
}
