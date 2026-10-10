# Database Design — WildGuard Wildlife Conservation System

## Database Instance
- **RDBMS:** PostgreSQL 18
- **Database Name:** `WildGuard`
- **Architecture:** Single database with four isolated schemas corresponding to the microservice ownership boundaries.
- **Cross-Service Coupling:** Loose coupling using UUIDs and logical keys (no cross-schema foreign keys).

---

## Schema 1: `wildlife` (Wildlife Service - Port 8081)

### Table: `wildlife.species`
- `id` (BIGSERIAL PRIMARY KEY)
- `common_name` (VARCHAR, NOT NULL)
- `scientific_name` (VARCHAR UNIQUE, NOT NULL)
- `conservation_status` (VARCHAR: LEAST_CONCERN, NEAR_THREATENED, VULNERABLE, ENDANGERED, CRITICALLY_ENDANGERED, EXTINCT_IN_WILD)
- `description` (TEXT)
- `habitat` (VARCHAR)
- `created_at`, `updated_at` (TIMESTAMP)

### Table: `wildlife.animals`
- `id` (BIGSERIAL PRIMARY KEY)
- `tag_id` (VARCHAR UNIQUE, NOT NULL) - Identification collar/tag
- `name` (VARCHAR) - Given individual name
- `species_id` (BIGINT) - Reference to `wildlife.species(id)`
- `species_name` (VARCHAR) - Denormalized for rapid query display
- `sex` (VARCHAR: MALE, FEMALE, UNKNOWN)
- `estimated_age` (INTEGER)
- `health_status` (VARCHAR: HEALTHY, INJURED, SICK, CRITICAL, DECEASED)
- `conservation_status` (VARCHAR)
- `current_protected_area_id` (VARCHAR) - Logical UUID reference to Protected Area Service
- `current_zone_id` (VARCHAR) - Logical UUID reference
- `current_area_name` (VARCHAR) - Denormalized
- `latitude`, `longitude` (DOUBLE PRECISION)
- `last_observed_date` (DATE)
- `notes` (TEXT) - Medical and behavioural history
- `created_at`, `updated_at` (TIMESTAMP)

### Table: `wildlife.animal_observations`
- `id` (BIGSERIAL PRIMARY KEY)
- `animal_id` (BIGINT, NOT NULL)
- `animal_tag_id` (VARCHAR)
- `observer_name` (VARCHAR, NOT NULL)
- `observation_date` (DATE, NOT NULL)
- `latitude`, `longitude` (DOUBLE PRECISION)
- `location` (VARCHAR)
- `behavior` (VARCHAR)
- `health_note` (VARCHAR)
- `notes` (TEXT)
- `created_at` (TIMESTAMP)

---

## Schema 2: `protected_areas` (Protected Area Service - Port 8082)

### Table: `protected_areas.protected_areas`
- `id` (BIGSERIAL PRIMARY KEY)
- `area_code` (VARCHAR UNIQUE, e.g. PA-001)
- `name` (VARCHAR, NOT NULL)
- `area_type` (VARCHAR: NATIONAL_PARK, TIGER_RESERVE, WILDLIFE_SANCTUARY, BIOSPHERE_RESERVE)
- `state` (VARCHAR)
- `country` (VARCHAR DEFAULT 'India')
- `total_area_km2` (DOUBLE PRECISION)
- `latitude`, `longitude` (DOUBLE PRECISION)
- `description` (TEXT)
- `conservation_authority` (VARCHAR)
- `status` (VARCHAR: ACTIVE, INACTIVE)
- `established_date` (DATE)
- `created_at`, `updated_at` (TIMESTAMP)

### Table: `protected_areas.zones`
- `id` (BIGSERIAL PRIMARY KEY)
- `zone_code` (VARCHAR UNIQUE, e.g. Z-001)
- `protected_area_id` (BIGINT, NOT NULL)
- `protected_area_name` (VARCHAR) - Denormalized
- `name` (VARCHAR, NOT NULL)
- `zone_type` (VARCHAR: CORE, BUFFER, TRANSITION, RESTRICTED)
- `area_km2` (DOUBLE PRECISION)
- `description` (TEXT)
- `center_latitude`, `center_longitude` (DOUBLE PRECISION)
- `habitat_type` (VARCHAR)
- `status` (VARCHAR: ACTIVE, INACTIVE)
- `created_at`, `updated_at` (TIMESTAMP)

### Table: `protected_areas.habitats`
- `id` (BIGSERIAL PRIMARY KEY)
- `zone_id` (BIGINT), `zone_name` (VARCHAR)
- `protected_area_id` (BIGINT), `protected_area_name` (VARCHAR)
- `habitat_type` (VARCHAR: TROPICAL_FOREST, DECIDUOUS_FOREST, GRASSLAND, WETLAND)
- `vegetation_type` (VARCHAR)
- `dominant_species` (VARCHAR)
- `condition` (VARCHAR: EXCELLENT, GOOD, FAIR, DEGRADED, CRITICAL)
- `coverage_km2` (DOUBLE PRECISION)
- `notes` (TEXT)
- `last_assessed_date` (DATE)
- `created_at`, `updated_at` (TIMESTAMP)

### Table: `protected_areas.water_sources`
- `id` (BIGSERIAL PRIMARY KEY)
- `name` (VARCHAR, NOT NULL)
- `zone_id` (BIGINT), `zone_name` (VARCHAR)
- `protected_area_id` (BIGINT), `protected_area_name` (VARCHAR)
- `source_type` (VARCHAR: RIVER, LAKE, POND, WATERHOLE, RESERVOIR)
- `condition` (VARCHAR: GOOD, FAIR, POLLUTED, DRY, SEASONAL)
- `latitude`, `longitude` (DOUBLE PRECISION)
- `last_inspection_date` (DATE)
- `inspection_notes` (TEXT)
- `created_at`, `updated_at` (TIMESTAMP)

---

## Schema 3: `patrol` (Patrol Service - Port 8083)

### Table: `patrol.staff`
- `id` (BIGSERIAL PRIMARY KEY)
- `staff_id` (VARCHAR UNIQUE, e.g. ST-001)
- `first_name`, `last_name` (VARCHAR, NOT NULL)
- `designation` (VARCHAR: FOREST_OFFICER, RANGER, GUARD, VETERINARIAN)
- `phone`, `email` (VARCHAR)
- `team` (VARCHAR)
- `availability` (VARCHAR: AVAILABLE, ON_PATROL, ON_LEAVE, INACTIVE)
- `assigned_area_id` (BIGINT), `assigned_area_name` (VARCHAR)
- `created_at`, `updated_at` (TIMESTAMP)

### Table: `patrol.vehicles`
- `id` (BIGSERIAL PRIMARY KEY)
- `vehicle_number` (VARCHAR UNIQUE)
- `vehicle_type` (VARCHAR: JEEP, TRUCK, MOTORCYCLE, BOAT)
- `model` (VARCHAR), `year` (INTEGER)
- `status` (VARCHAR: AVAILABLE, IN_USE, MAINTENANCE)
- `assigned_area_id` (BIGINT), `assigned_area_name` (VARCHAR)
- `condition` (VARCHAR: GOOD, FAIR, POOR)
- `created_at`, `updated_at` (TIMESTAMP)

### Table: `patrol.equipment`
- `id` (BIGSERIAL PRIMARY KEY)
- `equipment_code` (VARCHAR UNIQUE, e.g. EQ-001)
- `name` (VARCHAR, NOT NULL)
- `category` (VARCHAR: TRACKING, COMMUNICATION, FIRST_AID, FIREARM)
- `status` (VARCHAR: AVAILABLE, IN_USE, MAINTENANCE)
- `condition` (VARCHAR: GOOD, FAIR, POOR)
- `assigned_patrol_id` (VARCHAR)
- `created_at`, `updated_at` (TIMESTAMP)

### Table: `patrol.patrols`
- `id` (BIGSERIAL PRIMARY KEY)
- `patrol_id` (VARCHAR UNIQUE, e.g. PTR-001)
- `zone_id` (BIGINT), `zone_name` (VARCHAR)
- `protected_area_id` (BIGINT), `protected_area_name` (VARCHAR)
- `route_description` (TEXT)
- `planned_start_time` (TIMESTAMP, NOT NULL), `planned_end_time` (TIMESTAMP)
- `actual_start_time` (TIMESTAMP), `actual_end_time` (TIMESTAMP)
- `status` (VARCHAR: PLANNED, IN_PROGRESS, COMPLETED, CANCELLED)
- `observations` (TEXT)
- `outcome` (TEXT)
- `created_at`, `updated_at` (TIMESTAMP)

### Table: `patrol.patrol_staff_assignments`
- `id` (BIGSERIAL PRIMARY KEY)
- `patrol_id` (BIGINT), `staff_id` (BIGINT)
- `staff_name` (VARCHAR), `role` (VARCHAR: LEADER, MEMBER)
- `assigned_at` (TIMESTAMP)

### Table: `patrol.patrol_resource_assignments`
- `id` (BIGSERIAL PRIMARY KEY)
- `patrol_id` (BIGINT)
- `resource_type` (VARCHAR: VEHICLE, EQUIPMENT)
- `resource_id` (BIGINT), `resource_name` (VARCHAR)
- `assigned_at` (TIMESTAMP), `released_at` (TIMESTAMP)

---

## Schema 4: `incidents` (Incident Service - Port 8084)

### Table: `incidents.incidents`
- `id` (BIGSERIAL PRIMARY KEY)
- `incident_code` (VARCHAR UNIQUE, e.g. INC-001)
- `incident_type` (VARCHAR: WILDLIFE_INJURY, HUMAN_WILDLIFE_CONFLICT, POACHING, ILLEGAL_ACTIVITY, HABITAT_DESTRUCTION, WATER_RESOURCE_THREAT, FIRE)
- `description` (VARCHAR(1000), NOT NULL)
- `severity` (VARCHAR: LOW, MEDIUM, HIGH, CRITICAL)
- `reported_at` (TIMESTAMP, NOT NULL)
- `protected_area_id` (BIGINT), `protected_area_name` (VARCHAR)
- `zone_id` (BIGINT), `zone_name` (VARCHAR)
- `animal_id` (BIGINT), `animal_tag_id` (VARCHAR)
- `latitude`, `longitude` (DOUBLE PRECISION)
- `location_description` (VARCHAR(500))
- `assigned_personnel_id` (VARCHAR), `assigned_personnel_name` (VARCHAR)
- `status` (VARCHAR: REPORTED, UNDER_INVESTIGATION, IN_PROGRESS, RESOLVED, CLOSED)
- `response_notes` (VARCHAR(2000))
- `resolution_details` (VARCHAR(2000))
- `resolved_at` (TIMESTAMP)
- `created_at`, `updated_at` (TIMESTAMP)

### Table: `incidents.rescue_cases`
- `id` (BIGSERIAL PRIMARY KEY)
- `rescue_code` (VARCHAR UNIQUE, e.g. RC-001)
- `incident_id` (BIGINT), `incident_code` (VARCHAR)
- `animal_id` (BIGINT), `animal_tag_id` (VARCHAR), `animal_species` (VARCHAR)
- `rescue_location` (VARCHAR, NOT NULL)
- `rescue_date_time` (TIMESTAMP, NOT NULL)
- `rescue_team` (VARCHAR)
- `initial_condition` (VARCHAR(1000), NOT NULL)
- `treatment_details` (VARCHAR(2000))
- `rehabilitation_facility` (VARCHAR)
- `rehabilitation_notes` (VARCHAR(2000))
- `transfer_date_time` (TIMESTAMP), `transfer_location` (VARCHAR)
- `release_location` (VARCHAR), `release_date_time` (TIMESTAMP)
- `outcome` (VARCHAR)
- `status` (VARCHAR: REPORTED, RESCUED, UNDER_TREATMENT, REHABILITATION, TRANSFERRED, RELEASED, CLOSED)
- `created_at`, `updated_at` (TIMESTAMP)

### Table: `incidents.conservation_programmes`
- `id` (BIGSERIAL PRIMARY KEY)
- `programme_code` (VARCHAR UNIQUE, e.g. CP-001)
- `name` (VARCHAR, NOT NULL)
- `description` (VARCHAR(2000)), `objectives` (VARCHAR(2000))
- `target_protected_area_id` (BIGINT), `target_protected_area_name` (VARCHAR)
- `target_zone_id` (BIGINT), `target_zone_name` (VARCHAR)
- `start_date` (DATE, NOT NULL), `end_date` (DATE)
- `status` (VARCHAR: PLANNED, ACTIVE, COMPLETED, SUSPENDED, CANCELLED)
- `responsible_personnel_id` (VARCHAR), `responsible_personnel_name` (VARCHAR)
- `completion_percentage` (INTEGER DEFAULT 0)
- `created_at`, `updated_at` (TIMESTAMP)

### Table: `incidents.programme_activities`
- `id` (BIGSERIAL PRIMARY KEY)
- `programme_id` (BIGINT, NOT NULL)
- `activity_name` (VARCHAR, NOT NULL)
- `description` (VARCHAR(2000))
- `target_date` (DATE), `completed_date` (DATE)
- `status` (VARCHAR: PENDING, IN_PROGRESS, COMPLETED, CANCELLED)
- `progress_percent` (INTEGER DEFAULT 0)
- `notes` (VARCHAR(2000))
- `created_at`, `updated_at` (TIMESTAMP)
