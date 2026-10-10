# Testing Strategy & Execution — WildGuard Wildlife Conservation System

## Automated Verification Suite

Each microservice contains automated JUnit 5 and Spring Boot Test suites:
1. `com.wildguard.wildlife.WildlifeServiceApplicationTests`
2. `com.wildguard.protectedarea.ProtectedAreaServiceApplicationTests`
3. `com.wildguard.patrol.PatrolServiceApplicationTests`
4. `com.wildguard.incident.IncidentServiceApplicationTests`

## Running Full Integration Tests

Execute the Maven wrapper at the root directory:
```powershell
.\mvnw.cmd test
```

### Build & Verification Results
```
[INFO] Reactor Summary for WildGuard - Wildlife Conservation Management System 0.0.1-SNAPSHOT:
[INFO] 
[INFO] wildlife-service ................................... SUCCESS
[INFO] protected-area-service ............................. SUCCESS
[INFO] patrol-service ..................................... SUCCESS
[INFO] incident-service ................................... SUCCESS
[INFO] WildGuard - Wildlife Conservation Management System  SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
```

## Tested Functional Workflows

1. **Wildlife Management:**
   - Entity persistence into `wildlife` schema.
   - Species foreign key references and validation.
   - Search by tag ID, animal name, or species name.
   - Observation chronological tracking.

2. **Protected Area & Habitat:**
   - Multi-tier hierarchy: Protected Area -> Zones -> Habitats & Water Sources.
   - Coordinates centroid storage.
   - Condition inspection logging.

3. **Patrol & Resource Allocation:**
   - State machine transition: `PLANNED` -> `IN_PROGRESS` -> `COMPLETED`.
   - Resource collision rejection: When staff, vehicle, or equipment is `ON_PATROL` or `IN_USE`, allocation is rejected.
   - Resource release: On patrol completion, all attached staff and vehicles revert to `AVAILABLE`.

4. **Incident & Official Illustrative Scenario:**
   - Incident registration with severity tracking.
   - Filter query by `zoneId` + `reportedAt` date range (Current Month).
   - Rescue progression: `REPORTED` -> `RESCUED` -> `UNDER_TREATMENT` -> `REHABILITATION` -> `RELEASED`.
   - Programme activity progress aggregation: Automatically updates `completionPercentage` from activities average.
