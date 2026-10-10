# NatureSheild — Smart Wildlife Conservation & Protected Area Management

**Team 27 | VIT Enterprise Java Hackathon | October 10–11, 2026**

---

## Overview

NatureSheild is an enterprise-grade microservices platform for wildlife conservation and protected area management. Built with Java 17, Spring Boot 3.3.5, PostgreSQL, and Thymeleaf, it provides an integrated operational network for state wildlife authorities, reserve range officers, and field patrols.

## Architecture

Four independent Spring Boot microservices:

| Service | Port | Responsibility |
|---------|------|----------------|
| Wildlife Core Service | 8081 | Animals, species catalogue, observations, officer authentication, and central dashboard |
| Protected Area Service | 8082 | Protected areas & reserves, management zones, habitats, water sources |
| Patrol Operations Service | 8083 | Patrol logs & schedules, field staff squads, vehicles, equipment resources |
| Threat & Incident Service | 8084 | Incident tracking, rescue & rehabilitation cases, conservation programmes |

## Technology Stack

- **Language:** Java 17
- **Framework:** Spring Boot 3.3.5
- **Architecture:** Spring MVC + REST APIs
- **Frontend:** Thymeleaf + Bootstrap 5 + Clean Navy Theme
- **Database:** PostgreSQL (`WildGuard` database with 4 schemas: `wildlife`, `protected_areas`, `patrol`, `incidents`)
- **Persistence:** Spring Data JPA + Hibernate
- **Build Tool:** Maven

## Quick Launch

### 1. Database Setup
```sql
CREATE DATABASE "WildGuard";
\c WildGuard
CREATE SCHEMA wildlife;
CREATE SCHEMA protected_areas;
CREATE SCHEMA patrol;
CREATE SCHEMA incidents;
```

Or execute the provided SQL setup script:
```bash
psql -U postgres -d WildGuard -f scripts/setup-db.sql
```

### 2. Run All Microservices
From the root directory:
```bash
.\start-all.bat
```

Or run via Maven reactor:
```bash
.\mvnw.cmd spring-boot:run -pl wildlife-service
.\mvnw.cmd spring-boot:run -pl protected-area-service
.\mvnw.cmd spring-boot:run -pl patrol-service
.\mvnw.cmd spring-boot:run -pl incident-service
```

## Service URLs

- **Officer Login Gateway:** [http://localhost:8081/login](http://localhost:8081/login)
- **Operations Dashboard:** [http://localhost:8081/dashboard](http://localhost:8081/dashboard)
- **Protected Areas & Zones:** [http://localhost:8082/areas](http://localhost:8082/areas)
- **Patrol Logistics:** [http://localhost:8083/patrols](http://localhost:8083/patrols)
- **Threat & Incident Log:** [http://localhost:8084/incidents](http://localhost:8084/incidents)
- **Official Monthly Scenario Report:** [http://localhost:8084/incidents/report](http://localhost:8084/incidents/report)

## Documentation

Full project design documentation is located in the `docs/` folder:
- [Architecture](docs/ARCHITECTURE.md)
- [Database Design](docs/DATABASE_DESIGN.md)
- [API Documentation](docs/API_DOCUMENTATION.md)
- [Testing Guide](docs/TESTING.md)
- [Demo Guide](docs/DEMO_GUIDE.md)
