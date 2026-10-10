package com.wildguard.patrol.repository;

import com.wildguard.patrol.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    Optional<Vehicle> findByVehicleNumber(String vehicleNumber);
    List<Vehicle> findByStatus(String status);
    List<Vehicle> findByVehicleType(String vehicleType);
    long countByStatus(String status);
}
