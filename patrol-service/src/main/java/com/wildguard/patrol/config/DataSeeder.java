package com.wildguard.patrol.config;

import com.wildguard.patrol.entity.*;
import com.wildguard.patrol.service.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;

@Component
public class DataSeeder implements CommandLineRunner {
    private final StaffService staffService;
    private final VehicleService vehicleService;
    private final EquipmentService equipmentService;
    private final PatrolService patrolService;

    public DataSeeder(StaffService staffService, VehicleService vehicleService,
                      EquipmentService equipmentService, PatrolService patrolService) {
        this.staffService = staffService;
        this.vehicleService = vehicleService;
        this.equipmentService = equipmentService;
        this.patrolService = patrolService;
    }

    @Override
    public void run(String... args) {
        if (staffService.findAll().isEmpty()) {
            Staff s1 = new Staff(); s1.setStaffId("ST-CORB-01"); s1.setFirstName("Ravi"); s1.setLastName("Kumar");
            s1.setDesignation("FOREST_OFFICER"); s1.setPhone("+91-9876543210"); s1.setEmail("ravi.kumar@natureshield.gov.in");
            s1.setTeam("Alpha Strike Squad"); s1.setAvailability("AVAILABLE"); s1.setAssignedAreaName("Jim Corbett National Park"); staffService.save(s1);

            Staff s2 = new Staff(); s2.setStaffId("ST-RANTH-02"); s2.setFirstName("Priya"); s2.setLastName("Sharma");
            s2.setDesignation("RANGER"); s2.setPhone("+91-9876543211"); s2.setEmail("priya.sharma@natureshield.gov.in");
            s2.setTeam("Bravo Surveillance"); s2.setAvailability("AVAILABLE"); s2.setAssignedAreaName("Ranthambore Tiger Reserve"); staffService.save(s2);

            Staff s3 = new Staff(); s3.setStaffId("ST-CORB-03"); s3.setFirstName("Amit"); s3.setLastName("Singh");
            s3.setDesignation("GUARD"); s3.setPhone("+91-9876543212"); s3.setEmail("amit.singh@natureshield.gov.in");
            s3.setTeam("Alpha Strike Squad"); s3.setAvailability("AVAILABLE"); s3.setAssignedAreaName("Jim Corbett National Park"); staffService.save(s3);

            Staff s4 = new Staff(); s4.setStaffId("ST-KAZ-04"); s4.setFirstName("Dev"); s4.setLastName("Gogoi");
            s4.setDesignation("RANGER"); s4.setPhone("+91-9876543213"); s4.setEmail("dev.gogoi@natureshield.gov.in");
            s4.setTeam("Rhino Anti-Poaching Unit"); s4.setAvailability("AVAILABLE"); s4.setAssignedAreaName("Kaziranga National Park"); staffService.save(s4);

            // Vehicles
            Vehicle v1 = new Vehicle(); v1.setVehicleNumber("UK-04-NS-1001"); v1.setVehicleType("JEEP");
            v1.setModel("Mahindra Scorpio 4x4 Heavy Duty"); v1.setYear(2024); v1.setStatus("AVAILABLE"); v1.setCondition("EXCELLENT");
            v1.setAssignedAreaName("Jim Corbett National Park"); vehicleService.save(v1);

            Vehicle v2 = new Vehicle(); v2.setVehicleNumber("RJ-25-NS-2002"); v2.setVehicleType("MOTORCYCLE");
            v2.setModel("Royal Enfield Himalayan 450"); v2.setYear(2023); v2.setStatus("AVAILABLE"); v2.setCondition("GOOD");
            v2.setAssignedAreaName("Ranthambore Tiger Reserve"); vehicleService.save(v2);

            Vehicle v3 = new Vehicle(); v3.setVehicleNumber("AS-03-NS-3003"); v3.setVehicleType("BOAT");
            v3.setModel("Speed Riverboat Patrol-18"); v3.setYear(2024); v3.setStatus("AVAILABLE"); v3.setCondition("EXCELLENT");
            v3.setAssignedAreaName("Kaziranga National Park"); vehicleService.save(v3);

            // Equipment
            Equipment e1 = new Equipment(); e1.setEquipmentCode("EQ-OPT-01"); e1.setName("FLIR Thermal Handheld Scanner");
            e1.setCategory("TRACKING"); e1.setStatus("AVAILABLE"); e1.setCondition("EXCELLENT"); equipmentService.save(e1);

            Equipment e2 = new Equipment(); e2.setEquipmentCode("EQ-COM-02"); e2.setName("Motorola VHF High-Range Radio");
            e2.setCategory("COMMUNICATION"); e2.setStatus("AVAILABLE"); e2.setCondition("GOOD"); equipmentService.save(e2);

            Equipment e3 = new Equipment(); e3.setEquipmentCode("EQ-DRN-03"); e3.setName("DJI Matrice 300 RTK Aerial Drone");
            e3.setCategory("SURVEILLANCE"); e3.setStatus("AVAILABLE"); e3.setCondition("EXCELLENT"); equipmentService.save(e3);

            // Patrols
            Patrol p1 = new Patrol(); p1.setPatrolId("PTR-2026-081"); p1.setProtectedAreaName("Jim Corbett National Park");
            p1.setZoneName("Dhikala Core Zone"); p1.setRouteDescription("Dhikala Watchtower to Ramganga southern river boundary");
            p1.setPlannedStartTime(LocalDateTime.now().minusHours(3)); p1.setPlannedEndTime(LocalDateTime.now().plusHours(3));
            p1.setStatus("IN_PROGRESS"); p1.setActualStartTime(LocalDateTime.now().minusHours(3));
            p1.setObservations("Boundary fence check intact; tiger pugmarks TR-CORB-01 verified near riverbed.");
            patrolService.save(p1);
            patrolService.assignStaff(p1.getId(), s1.getId(), "LEADER");
            patrolService.assignVehicle(p1.getId(), v1.getId());

            Patrol p2 = new Patrol(); p2.setPatrolId("PTR-2026-082"); p2.setProtectedAreaName("Kaziranga National Park");
            p2.setZoneName("Central Range Kohora"); p2.setRouteDescription("Kohora river channel inspection for flood silt levels");
            p2.setPlannedStartTime(LocalDateTime.now().minusHours(1)); p2.setPlannedEndTime(LocalDateTime.now().plusHours(5));
            p2.setStatus("IN_PROGRESS"); p2.setActualStartTime(LocalDateTime.now().minusHours(1));
            p2.setObservations("Rhino herd grazing peacefully; no unauthorized craft spotted.");
            patrolService.save(p2);
            patrolService.assignStaff(p2.getId(), s4.getId(), "LEADER");
            patrolService.assignVehicle(p2.getId(), v3.getId());
        }
    }
}
