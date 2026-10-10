package com.wildguard.protectedarea.repository;

import com.wildguard.protectedarea.entity.ProtectedArea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProtectedAreaRepository extends JpaRepository<ProtectedArea, Long> {
    Optional<ProtectedArea> findByAreaCode(String areaCode);
    List<ProtectedArea> findByStatus(String status);
    List<ProtectedArea> findByState(String state);
    List<ProtectedArea> findByNameContainingIgnoreCase(String name);
    long countByStatus(String status);
}
