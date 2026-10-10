package com.wildguard.incident.seeder;

import com.wildguard.incident.entity.ConservationProgramme;
import com.wildguard.incident.entity.Incident;
import com.wildguard.incident.entity.ProgrammeActivity;
import com.wildguard.incident.entity.RescueCase;
import com.wildguard.incident.repository.ConservationProgrammeRepository;
import com.wildguard.incident.repository.IncidentRepository;
import com.wildguard.incident.repository.ProgrammeActivityRepository;
import com.wildguard.incident.repository.RescueCaseRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
public class DataSeeder implements CommandLineRunner {

    private final IncidentRepository incidentRepo;
    private final RescueCaseRepository rescueRepo;
    private final ConservationProgrammeRepository cpRepo;
    private final ProgrammeActivityRepository activityRepo;

    public DataSeeder(IncidentRepository incidentRepo, RescueCaseRepository rescueRepo, 
                      ConservationProgrammeRepository cpRepo, ProgrammeActivityRepository activityRepo) {
        this.incidentRepo = incidentRepo;
        this.rescueRepo = rescueRepo;
        this.cpRepo = cpRepo;
        this.activityRepo = activityRepo;
    }

    @Override
    public void run(String... args) throws Exception {
        if (incidentRepo.count() == 0) {
            seedIncidents();
        }
        if (rescueRepo.count() == 0) {
            seedRescues();
        }
        if (cpRepo.count() == 0) {
            seedProgrammes();
        }
    }

    private void seedIncidents() {
        Incident i1 = new Incident();
        i1.setIncidentCode("INC-2026-001");
        i1.setIncidentType("POACHING");
        i1.setDescription("Suspicious metal snare traps recovered along Ramganga river boundary embankment.");
        i1.setSeverity("CRITICAL");
        i1.setReportedAt(LocalDateTime.of(2026, 10, 5, 14, 30));
        i1.setZoneId(1L);
        i1.setZoneName("Dhikala Core Zone");
        i1.setStatus("IN_PROGRESS");
        i1.setAssignedPersonnelName("Ravi Kumar (Range Officer)");
        incidentRepo.save(i1);

        Incident i2 = new Incident();
        i2.setIncidentCode("INC-2026-002");
        i2.setIncidentType("WILDLIFE_INJURY");
        i2.setDescription("Injured elephant calf reported near forest border fringe by local village head.");
        i2.setSeverity("HIGH");
        i2.setReportedAt(LocalDateTime.of(2026, 10, 10, 9, 15));
        i2.setZoneId(2L);
        i2.setZoneName("Bijrani Buffer Zone");
        i2.setStatus("REPORTED");
        i2.setAssignedPersonnelName("Priya Sharma (Veterinary Officer)");
        incidentRepo.save(i2);

        Incident i3 = new Incident();
        i3.setIncidentCode("INC-2026-003");
        i3.setIncidentType("FIRE");
        i3.setDescription("Controlled dry grass understory wildfire along boundary ridge successfully quenched.");
        i3.setSeverity("MEDIUM");
        i3.setReportedAt(LocalDateTime.of(2026, 10, 1, 16, 45));
        i3.setZoneId(1L);
        i3.setZoneName("Dhikala Core Zone");
        i3.setStatus("RESOLVED");
        i3.setAssignedPersonnelName("Amit Singh (Head Forester)");
        i3.setResolutionDetails("Extinguished via counter-firing and portable pressurized water backpack pumps.");
        incidentRepo.save(i3);

        Incident i4 = new Incident();
        i4.setIncidentCode("INC-2026-004");
        i4.setIncidentType("HUMAN_WILDLIFE_CONFLICT");
        i4.setDescription("Elephants entered bordering sugarcane agricultural lands; herd safely guided back.");
        i4.setSeverity("HIGH");
        i4.setReportedAt(LocalDateTime.of(2026, 9, 28, 20, 0));
        i4.setZoneId(1L);
        i4.setZoneName("Dhikala Core Zone");
        i4.setStatus("RESOLVED");
        i4.setAssignedPersonnelName("Ravi Kumar (Range Officer)");
        i4.setResolutionDetails("Solar fences inspected; spotlights and non-lethal acoustic repellents used.");
        incidentRepo.save(i4);
        
        Incident i5 = new Incident();
        i5.setIncidentCode("INC-2026-005");
        i5.setIncidentType("ILLEGAL_ACTIVITY");
        i5.setDescription("Unauthorized vehicular trespass along restricted logging path detected via camera trap.");
        i5.setSeverity("MEDIUM");
        i5.setReportedAt(LocalDateTime.of(2026, 10, 8, 10, 0));
        i5.setZoneId(2L);
        i5.setZoneName("Bijrani Buffer Zone");
        i5.setStatus("UNDER_INVESTIGATION");
        i5.setAssignedPersonnelName("Amit Singh (Head Forester)");
        incidentRepo.save(i5);

        Incident i6 = new Incident();
        i6.setIncidentCode("INC-2026-006");
        i6.setIncidentType("HABITAT_DESTRUCTION");
        i6.setDescription("Perimeter chain-link solar fencing compromised by fallen sal tree trunk.");
        i6.setSeverity("LOW");
        i6.setReportedAt(LocalDateTime.of(2026, 10, 9, 11, 0));
        i6.setZoneId(1L);
        i6.setZoneName("Dhikala Core Zone");
        i6.setStatus("RESOLVED");
        i6.setAssignedPersonnelName("Dev Gogoi (Field Ranger)");
        i6.setResolutionDetails("Fallen timber cleared; insulators and solar line tensioners reinstalled.");
        incidentRepo.save(i6);
        
        Incident i7 = new Incident();
        i7.setIncidentCode("INC-2026-007");
        i7.setIncidentType("DISEASE_OUTBREAK");
        i7.setDescription("Vulture roosting tree inspection noted abnormal weakness symptoms in two juveniles.");
        i7.setSeverity("CRITICAL");
        i7.setReportedAt(LocalDateTime.of(2026, 10, 2, 8, 0));
        i7.setZoneId(1L);
        i7.setZoneName("Dhikala Core Zone");
        i7.setStatus("IN_PROGRESS");
        i7.setAssignedPersonnelName("Priya Sharma (Veterinary Officer)");
        incidentRepo.save(i7);

        Incident i8 = new Incident();
        i8.setIncidentCode("INC-2026-008");
        i8.setIncidentType("OTHER");
        i8.setDescription("Unregistered safari jeep broke down in buffer sector past sunset curfew.");
        i8.setSeverity("MEDIUM");
        i8.setReportedAt(LocalDateTime.of(2026, 10, 7, 18, 0));
        i8.setZoneId(2L);
        i8.setZoneName("Bijrani Buffer Zone");
        i8.setStatus("CLOSED");
        i8.setAssignedPersonnelName("Ravi Kumar (Range Officer)");
        i8.setResolutionDetails("Tourists escorted out safely; driver cited under Wildlife Protection Act.");
        incidentRepo.save(i8);
    }

    private void seedRescues() {
        RescueCase r1 = new RescueCase();
        r1.setRescueCode("RC-2026-001");
        r1.setAnimalSpecies("Asian Elephant");
        r1.setRescueLocation("Ramganga Western Floodplain");
        r1.setRescueDateTime(LocalDateTime.of(2026, 10, 10, 10, 0));
        r1.setInitialCondition("Deep puncture wound near right front foot; distressed");
        r1.setStatus("UNDER_TREATMENT");
        rescueRepo.save(r1);

        RescueCase r2 = new RescueCase();
        r2.setRescueCode("RC-2026-002");
        r2.setAnimalSpecies("Bengal Tiger");
        r2.setRescueLocation("Central Grassland Ravine");
        r2.setRescueDateTime(LocalDateTime.of(2026, 9, 15, 14, 0));
        r2.setInitialCondition("Sub-adult male dehydrated following territorial skirmish");
        r2.setStatus("RELEASED");
        rescueRepo.save(r2);
        
        RescueCase r3 = new RescueCase();
        r3.setRescueCode("RC-2026-003");
        r3.setAnimalSpecies("Indian Rhinoceros");
        r3.setRescueLocation("South Riverine Marsh Border");
        r3.setRescueDateTime(LocalDateTime.of(2026, 10, 5, 8, 0));
        r3.setInitialCondition("Calf separated from mother during flash monsoon surge");
        r3.setStatus("UNDER_TREATMENT");
        rescueRepo.save(r3);
        
        RescueCase r4 = new RescueCase();
        r4.setRescueCode("RC-2026-004");
        r4.setAnimalSpecies("Sloth Bear");
        r4.setRescueLocation("East Boundary Rocky Outcrop");
        r4.setRescueDateTime(LocalDateTime.of(2026, 10, 8, 11, 30));
        r4.setInitialCondition("Snare cable entangling rear paw; mild laceration");
        r4.setStatus("REHABILITATION");
        rescueRepo.save(r4);
    }

    private void seedProgrammes() {
        ConservationProgramme p1 = new ConservationProgramme();
        p1.setProgrammeCode("CP-2026-TIG");
        p1.setName("Project Tiger Corridor Fortification");
        p1.setStartDate(LocalDate.of(2026, 1, 1));
        p1.setStatus("ACTIVE");
        p1.setCompletionPercentage(65);
        p1 = cpRepo.save(p1);

        ProgrammeActivity a1 = new ProgrammeActivity();
        a1.setProgrammeId(p1.getId());
        a1.setActivityName("Field training for 40 anti-poaching forest guards");
        a1.setStatus("COMPLETED");
        a1.setProgressPercent(100);
        activityRepo.save(a1);
        
        ProgrammeActivity a2 = new ProgrammeActivity();
        a2.setProgrammeId(p1.getId());
        a2.setActivityName("Installation of 24 solar-powered infrared camera traps");
        a2.setStatus("IN_PROGRESS");
        a2.setProgressPercent(75);
        activityRepo.save(a2);

        ProgrammeActivity a3 = new ProgrammeActivity();
        a3.setProgrammeId(p1.getId());
        a3.setActivityName("Community village sensitization along buffer villages");
        a3.setStatus("IN_PROGRESS");
        a3.setProgressPercent(40);
        activityRepo.save(a3);

        ConservationProgramme p2 = new ConservationProgramme();
        p2.setProgrammeCode("CP-2026-RIV");
        p2.setName("Ramganga Riverine Wetland Restoration");
        p2.setStartDate(LocalDate.of(2026, 6, 1));
        p2.setStatus("ACTIVE");
        p2.setCompletionPercentage(30);
        p2 = cpRepo.save(p2);
    }
}
