# WildGuard — Smart Wildlife Conservation & Protected Area Management

**Team 27 | VIT Enterprise Java Hackathon | October 10–11, 2026**

---

## Overview

WildGuard is an enterprise-grade microservices application for wildlife conservation and protected area management. Built with Java 17, Spring Boot 3.3.5, PostgreSQL 18, and Thymeleaf, it provides a fully integrated operational platform for wildlife authorities.

## Architecture

Four independent Spring Boot microservices:

| Service | Port | Responsibility |
|---------|------|----------------|
| Wildlife Service | 8081 | Animals, species, observations, dashboard |
| Protected Area Service | 8082 | Protected areas, zones, habitats, water sources |
| Patrol Service | 8083 | Patrols, field staff, vehicles, equipment |
| Incident Service | 8084 | Incidents, rescues, conservation programmes |

## Technology Stack

- **Language:** Java 17
- **Framework:** Spring Boot 3.3.5
- **Frontend:** Thymeleaf + Bootstrap 5 + Custom CSS
- **Database:** PostgreSQL 18 (`WildGuard` database)
- **Persistence:** Spring Data JPA + Hibernate
- **Build:** Maven (with Maven Wrapper)

## Prerequisites

- Java 17 or later
- PostgreSQL 18 running on port 5432
- Maven 3.9+ (or use included mvnw wrapper)
- `WildGuard` PostgreSQL database created

## Quick Setup

### 1. Database Setup

```sql
-- Connect as postgres and run:
CREATE DATABASE "WildGuard";
\c WildGuard
CREATE SCHEMA wildlife;
CREATE SCHEMA protected_areas;
CREATE SCHEMA patrol;
CREATE SCHEMA incidents;
```

Or use the provided script:
```bash
psql -U postgres -d WildGuard -f scripts/setup-db.sql
```

### 2. Configure Database Password

Update the password in each service's `application.properties`:
```properties
spring.datasource.password=YOUR_POSTGRES_PASSWORD
```

Default configured: `780321`

### 3. Start All Services

**Windows:**
```bat
start-all.bat
```

**Or start individually:**
```bash
cd wildlife-service && mvnw.cmd spring-boot:run
cd protected-area-service && mvnw.cmd spring-boot:run
cd patrol-service && mvnw.cmd spring-boot:run
cd incident-service && mvnw.cmd spring-boot:run
```

## Application URLs

| URL | Description |
|-----|-------------|
| http://localhost:8081 | Wildlife Service (main dashboard) |
| http://localhost:8081/dashboard | Operations Dashboard |
| http://localhost:8081/animals | Animal Records |
| http://localhost:8081/species | Species Catalogue |
| http://localhost:8082 | Protected Areas |
| http://localhost:8082/areas | Protected Area List |
| http://localhost:8082/zones | Zone Management |
| http://localhost:8082/habitats | Habitat Records |
| http://localhost:8082/water-sources | Water Resources |
| http://localhost:8083 | Patrol Operations |
| http://localhost:8083/staff | Field Staff |
| http://localhost:8083/resources | Vehicles & Equipment |
| http://localhost:8084 | Incident Management |
| http://localhost:8084/rescues | Rescue & Rehabilitation |
| http://localhost:8084/programmes | Conservation Programmes |
| http://localhost:8084/incidents/report | Monthly Incident Report |

## REST API Endpoints

| Service | Base Path |
|---------|-----------|
| Wildlife | `GET /api/wildlife/animals` |
| Protected Areas | `GET /api/protected-areas` |
| Patrols | `GET /api/patrols` |
| Incidents | `GET /api/incidents` |
| Health Checks | `GET /api/*/health` |

## Demo Data

Each service automatically seeds realistic demonstration data on first startup:
- 5 species, 10 animals, 5 observations (Wildlife)
- 3 protected areas, 6 zones, 4 habitats, 4 water sources (Protected Areas)
- 6 staff, 3 vehicles, 4 equipment, 3 patrols (Patrol)
- 8 incidents, 4 rescue cases, 2 conservation programmes (Incident)

## Key Workflows

1. **Monthly Incident Report:** `http://localhost:8084/incidents/report`
2. **Register Animal:** `http://localhost:8081/animals/new`
3. **Create Patrol:** `http://localhost:8083/patrols/new`
4. **Report Incident:** `http://localhost:8084/incidents/new`
5. **Track Rescue:** `http://localhost:8084/rescues`
6. **Conservation Programmes:** `http://localhost:8084/programmes`

## Documentation

See `docs/` folder for:
- [Architecture](docs/ARCHITECTURE.md)
- [Database Design](docs/DATABASE_DESIGN.md)
- [API Documentation](docs/API_DOCUMENTATION.md)
- [Testing Guide](docs/TESTING.md)
- [Demo Guide](docs/DEMO_GUIDE.md)
