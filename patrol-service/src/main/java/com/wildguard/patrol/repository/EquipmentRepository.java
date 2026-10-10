package com.wildguard.patrol.repository;

import com.wildguard.patrol.entity.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface EquipmentRepository extends JpaRepository<Equipment, Long> {
    Optional<Equipment> findByEquipmentCode(String equipmentCode);
    List<Equipment> findByStatus(String status);
    List<Equipment> findByCategory(String category);
    long countByStatus(String status);
}
