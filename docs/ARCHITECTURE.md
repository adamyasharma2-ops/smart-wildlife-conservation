# Architecture — WildGuard Wildlife Conservation System

## System Overview

WildGuard is a modular microservices application composed of four independently deployable Spring Boot services communicating via RESTful HTTP APIs.

```
┌─────────────────────────────────────────────────────────────────┐
│                    WildGuard System                              │
│                                                                 │
│  ┌──────────────┐  ┌──────────────┐  ┌──────────────┐  ┌──────────────┐ │
│  │  Wildlife    │  │  Protected   │  │   Patrol     │  │  Incident    │ │
│  │  Service     │  │  Area Svc    │  │  Service     │  │  Service     │ │
│  │  :8081       │  │  :8082       │  │  :8083       │  │  :8084       │ │
│  └──────┬───────┘  └──────┬───────┘  └──────┬───────┘  └──────┬───────┘ │
│         │                │                  │                  │         │
│         └────────────────┴──────────────────┴──────────────────┘         │
│                              REST HTTP APIs                               │
│                                                                           │
│  ┌────────────────────────────────────────────────────────────┐           │
│  │           PostgreSQL 18 — "WildGuard" Database              │           │
│  │  ┌──────────┐ ┌─────────────────┐ ┌────────┐ ┌──────────┐ │           │
│  │  │ wildlife │ │ protected_areas  │ │ patrol │ │incidents │ │           │
│  │  │  schema  │ │     schema       │ │ schema │ │  schema  │ │           │
│  │  └──────────┘ └─────────────────┘ └────────┘ └──────────┘ │           │
│  └────────────────────────────────────────────────────────────┘           │
└───────────────────────────────────────────────────────────────────────────┘
```

## Service Descriptions

### Service A — Wildlife Service (Port 8081)

**Primary responsibilities:**
- Species catalogue management (common name, scientific name, conservation status)
- Individual animal records (tag ID, health status, location, sex, age)
- Animal observation history
- Main operational dashboard (aggregates data from all 4 services)

**Schema:** `wildlife`  
**Tables:** `species`, `animals`, `animal_observations`  
**Base REST path:** `/api/wildlife`

### Service B — Protected Area Service (Port 8082)

**Primary responsibilities:**
- Protected area records (national parks, tiger reserves, sanctuaries)
- Zone management (core, buffer, patrol zones)
- Habitat records and condition assessment
- Water source monitoring and inspection tracking

**Schema:** `protected_areas`  
**Tables:** `protected_areas`, `zones`, `habitats`, `water_sources`  
**Base REST path:** `/api/protected-areas`

### Service C — Patrol Service (Port 8083)

**Primary responsibilities:**
- Patrol scheduling, assignment, and lifecycle management
- Field staff records and availability tracking
- Vehicle inventory and allocation
- Equipment inventory and allocation
- Resource availability enforcement (rejects double-allocation)

**Schema:** `patrol`  
**Tables:** `staff`, `vehicles`, `equipment`, `patrols`, `patrol_staff_assignments`, `patrol_resource_assignments`  
**Base REST path:** `/api/patrols`

### Service D — Incident Service (Port 8084)

**Primary responsibilities:**
- Wildlife incident reporting and response tracking
- Rescue case management and rehabilitation status
- Conservation programme management and progress tracking
- Monthly incident reports (the official demonstration scenario)

**Schema:** `incidents`  
**Tables:** `incidents`, `rescue_cases`, `conservation_programmes`, `programme_activities`  
**Base REST paths:** `/api/incidents`, `/api/rescues`, `/api/conservation-programmes`

## Inter-Service Communication

Services communicate via HTTP REST calls using Spring's `RestClient`. URLs are configurable via application properties:

```properties
services.wildlife.url=http://localhost:8081
services.protected-area.url=http://localhost:8082
services.patrol.url=http://localhost:8083
services.incident.url=http://localhost:8084
```

### Communication Patterns

| Caller | Callee | Purpose |
|--------|--------|---------|
| Wildlife Service | Protected Area Service | Get protected area names for animal display |
| Wildlife Service | Patrol Service | Get active patrol count for dashboard |
| Wildlife Service | Incident Service | Get unresolved incident count for dashboard |
| Patrol Service | Protected Area Service | Validate zone references |
| Incident Service | Wildlife Service | Get animal details for incident display |
| Incident Service | Protected Area Service | Get zone/area details for incident display |

### Graceful Degradation

All cross-service calls include:
- Connection timeout: 3 seconds
- Read timeout: 5 seconds
- If a downstream service is unavailable, the caller shows `N/A` or `Service Unavailable` rather than crashing

## Cross-Service Data References

To avoid tight coupling, services do **not** share JPA entities across schema boundaries. Instead:

- **UUIDs or stable IDs** are stored as plain string/long fields in the owning entity
- **Denormalized name fields** (e.g., `zoneName`, `protectedAreaName`) are stored alongside IDs to avoid constant cross-service lookups
- This means each service can operate independently even if peer services are temporarily unavailable

## Technology Stack

| Component | Technology |
|-----------|-----------|
| Language | Java 17 |
| Framework | Spring Boot 3.3.5 |
| Web | Spring MVC |
| Frontend | Thymeleaf 3 + Bootstrap 5 |
| Persistence | Spring Data JPA + Hibernate 6 |
| Database | PostgreSQL 18 |
| Validation | Jakarta Bean Validation 3 |
| Build | Apache Maven 3.9 |
| Testing | JUnit 5 + Spring Boot Test |

## Ports Summary

| Service | Port | Dashboard URL |
|---------|------|---------------|
| Wildlife Service | 8081 | http://localhost:8081/dashboard |
| Protected Area Service | 8082 | http://localhost:8082/areas |
| Patrol Service | 8083 | http://localhost:8083/patrols |
| Incident Service | 8084 | http://localhost:8084/incidents |
