package com.wildguard.patrol.service;

import com.wildguard.patrol.entity.*;
import com.wildguard.patrol.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class PatrolService {
    private final PatrolRepository patrolRepository;
    private final StaffRepository staffRepository;
    private final VehicleRepository vehicleRepository;
    private final EquipmentRepository equipmentRepository;
    private final PatrolStaffAssignmentRepository staffAssignmentRepository;
    private final PatrolResourceAssignmentRepository resourceAssignmentRepository;

    public PatrolService(PatrolRepository patrolRepository, StaffRepository staffRepository,
                         VehicleRepository vehicleRepository, EquipmentRepository equipmentRepository,
                         PatrolStaffAssignmentRepository staffAssignmentRepository,
                         PatrolResourceAssignmentRepository resourceAssignmentRepository) {
        this.patrolRepository = patrolRepository;
        this.staffRepository = staffRepository;
        this.vehicleRepository = vehicleRepository;
        this.equipmentRepository = equipmentRepository;
        this.staffAssignmentRepository = staffAssignmentRepository;
        this.resourceAssignmentRepository = resourceAssignmentRepository;
    }

    public List<Patrol> findAll() { return patrolRepository.findAll(); }
    public Optional<Patrol> findById(Long id) { return patrolRepository.findById(id); }
    public Patrol save(Patrol patrol) {
        if (patrol.getStatus() == null) patrol.setStatus("PLANNED");
        return patrolRepository.save(patrol);
    }
    public void deleteById(Long id) { patrolRepository.deleteById(id); }

    @Transactional
    public boolean assignStaff(Long patrolId, Long staffId, String role) {
        Staff staff = staffRepository.findById(staffId).orElse(null);
        if (staff == null || !"AVAILABLE".equalsIgnoreCase(staff.getAvailability())) {
            return false; // Rejected double-allocation
        }
        PatrolStaffAssignment psa = new PatrolStaffAssignment();
        psa.setPatrolId(patrolId);
        psa.setStaffId(staffId);
        psa.setStaffName(staff.getFullName());
        psa.setRole(role != null ? role : "MEMBER");
        psa.setAssignedAt(LocalDateTime.now());
        staffAssignmentRepository.save(psa);

        staff.setAvailability("ON_PATROL");
        staffRepository.save(staff);
        return true;
    }

    @Transactional
    public boolean assignVehicle(Long patrolId, Long vehicleId) {
        Vehicle v = vehicleRepository.findById(vehicleId).orElse(null);
        if (v == null || !"AVAILABLE".equalsIgnoreCase(v.getStatus())) {
            return false; // Rejected
        }
        PatrolResourceAssignment pra = new PatrolResourceAssignment();
        pra.setPatrolId(patrolId);
        pra.setResourceType("VEHICLE");
        pra.setResourceId(vehicleId);
        pra.setResourceName(v.getVehicleNumber() + " (" + v.getVehicleType() + ")");
        pra.setAssignedAt(LocalDateTime.now());
        resourceAssignmentRepository.save(pra);

        v.setStatus("IN_USE");
        vehicleRepository.save(v);
        return true;
    }

    @Transactional
    public boolean assignEquipment(Long patrolId, Long equipmentId) {
        Equipment eq = equipmentRepository.findById(equipmentId).orElse(null);
        if (eq == null || !"AVAILABLE".equalsIgnoreCase(eq.getStatus())) {
            return false;
        }
        PatrolResourceAssignment pra = new PatrolResourceAssignment();
        pra.setPatrolId(patrolId);
        pra.setResourceType("EQUIPMENT");
        pra.setResourceId(equipmentId);
        pra.setResourceName(eq.getName() + " (" + eq.getEquipmentCode() + ")");
        pra.setAssignedAt(LocalDateTime.now());
        resourceAssignmentRepository.save(pra);

        eq.setStatus("IN_USE");
        eq.setAssignedPatrolId(String.valueOf(patrolId));
        equipmentRepository.save(eq);
        return true;
    }

    @Transactional
    public void startPatrol(Long patrolId) {
        patrolRepository.findById(patrolId).ifPresent(p -> {
            p.setStatus("IN_PROGRESS");
            p.setActualStartTime(LocalDateTime.now());
            patrolRepository.save(p);
        });
    }

    @Transactional
    public void completePatrol(Long patrolId, String observations, String outcome) {
        patrolRepository.findById(patrolId).ifPresent(p -> {
            p.setStatus("COMPLETED");
            p.setActualEndTime(LocalDateTime.now());
            p.setObservations(observations);
            p.setOutcome(outcome);
            patrolRepository.save(p);

            // Release staff
            List<PatrolStaffAssignment> staffAssignments = staffAssignmentRepository.findByPatrolId(patrolId);
            for (PatrolStaffAssignment psa : staffAssignments) {
                staffRepository.findById(psa.getStaffId()).ifPresent(s -> {
                    s.setAvailability("AVAILABLE");
                    staffRepository.save(s);
                });
            }

            // Release resources
            List<PatrolResourceAssignment> resAssignments = resourceAssignmentRepository.findByPatrolId(patrolId);
            for (PatrolResourceAssignment pra : resAssignments) {
                pra.setReleasedAt(LocalDateTime.now());
                resourceAssignmentRepository.save(pra);
                if ("VEHICLE".equalsIgnoreCase(pra.getResourceType())) {
                    vehicleRepository.findById(pra.getResourceId()).ifPresent(v -> {
                        v.setStatus("AVAILABLE");
                        vehicleRepository.save(v);
                    });
                } else if ("EQUIPMENT".equalsIgnoreCase(pra.getResourceType())) {
                    equipmentRepository.findById(pra.getResourceId()).ifPresent(e -> {
                        e.setStatus("AVAILABLE");
                        e.setAssignedPatrolId(null);
                        equipmentRepository.save(e);
                    });
                }
            }
        });
    }

    public List<PatrolStaffAssignment> getStaffAssignments(Long patrolId) {
        return staffAssignmentRepository.findByPatrolId(patrolId);
    }

    public List<PatrolResourceAssignment> getResourceAssignments(Long patrolId) {
        return resourceAssignmentRepository.findByPatrolId(patrolId);
    }

    public Map<String, Object> getStats() {
        Map<String, Object> stats = new HashMap<>();
        stats.put("totalPatrols", patrolRepository.count());
        stats.put("activePatrols", patrolRepository.countByStatus("IN_PROGRESS"));
        stats.put("plannedPatrols", patrolRepository.countByStatus("PLANNED"));
        stats.put("completedPatrols", patrolRepository.countByStatus("COMPLETED"));
        stats.put("availableStaff", staffRepository.countByAvailability("AVAILABLE"));
        return stats;
    }
}
