# Demo Guide — WildGuard Wildlife Conservation System

**For evaluators — follow this script for a complete demonstration**

## Prerequisites

1. PostgreSQL 18 running with `WildGuard` database and 4 schemas
2. All 4 services started (run `start-all.bat` or start individually)
3. Wait ~60 seconds for all services to fully initialize and seed data
4. Open a browser at http://localhost:8081

---

## Demo Script (15 minutes)

### Step 1 — Dashboard Overview (2 min)

Navigate to: **http://localhost:8081/dashboard**

Show:
- Total animals registered, species count
- Healthy vs. injured/critical animals
- Active patrols (from Patrol Service)
- Unresolved incidents (from Incident Service)
- Recent observations table
- Active conservation programmes count

**Key point:** All numbers are live database counts pulled from 4 different services.

---

### Step 2 — Register a New Animal (2 min) [Workflow A]

1. Click **Wildlife Records → Animals** (or http://localhost:8081/animals)
2. Click **Add Animal**
3. Fill in:
   - Tag ID: `WL-DEMO-01`
   - Name: `Shera`
   - Species: Select `Bengal Tiger`
   - Sex: `Male`
   - Estimated Age: `4`
   - Health Status: `Healthy`
   - Conservation Status: `Endangered`
4. Click **Save**
5. View the new animal's detail page
6. Click **Edit** → Change Health Status to `Injured` → Save
7. Refresh page — confirm `Injured` status persists in PostgreSQL

---

### Step 3 — Protected Areas & Zones (1 min)

Navigate to: **http://localhost:8082/areas**

Show:
- 3 pre-seeded protected areas (Jim Corbett, Ranthambore, Kaziranga)
- Click any area → view zones, habitats, water sources

---

### Step 4 — Plan and Execute a Patrol (3 min) [Workflow B]

1. Navigate to: **http://localhost:8083/patrols**
2. Click **Create Patrol**
3. Fill in zone, area, schedule, route description
4. Save the patrol
5. Open the patrol detail page
6. Click **Assign Staff** → select an available staff member
7. Click **Assign Vehicle** → select an available vehicle
8. Attempt to assign an already-assigned vehicle → **blocked by validation** (Workflow G)
9. Click **Start Patrol** → status changes to `IN_PROGRESS`
10. Click **Complete Patrol** → enter observations → status changes to `COMPLETED`
11. Resources released automatically

---

### Step 5 — Report an Incident (2 min) [Workflow C]

1. Navigate to: **http://localhost:8084/incidents**
2. Click **Report Incident**
3. Fill in:
   - Type: `Poaching`
   - Severity: `HIGH`
   - Zone: `Z-001`
   - Description: `Suspected poaching activity near northern border`
   - Assign Personnel: `ST-001`
4. Save → Incident appears in list with `REPORTED` status
5. Click the incident → click **Update Status** → change to `UNDER_INVESTIGATION`
6. Show incident in dashboard count

---

### Step 6 — Monthly Incident Report [Workflow E — OFFICIAL SCENARIO]

Navigate to: **http://localhost:8084/incidents/report**

This is the key scenario from the problem statement:
1. Select **Protected Zone** (enter Zone ID or name)
2. Select **Month**: October 2026 (current month)
3. Click **Generate Report**
4. Results show: Incident Code, Type, Severity, Status, Location, Reported Date, Assigned Personnel
5. Demonstrate with a different zone/month to show dynamic filtering

---

### Step 7 — Rescue & Rehabilitation (2 min) [Workflow D]

1. Navigate to: **http://localhost:8084/rescues**
2. Click **Register Rescue Case**
3. Link to the incident created in Step 5
4. Enter initial condition, rescue team
5. Save → Status: `RESCUED`
6. Click **Update Status** → change to `UNDER_TREATMENT` → add treatment notes
7. Change to `REHABILITATION`
8. Click **Record Release** → enter release details → `RELEASED`
9. Show history is fully preserved

---

### Step 8 — Conservation Programme (1 min) [Workflow F]

1. Navigate to: **http://localhost:8084/programmes**
2. Open any pre-seeded programme (e.g., Tiger Conservation Initiative)
3. Show activities and progress bars
4. Click **Update Activity** → mark an activity as 100% complete
5. Show completion percentage recalculates from activities

---

### Step 9 — REST API Demo (1 min) [Enterprise Integration]

Open browser to:
- http://localhost:8081/api/wildlife/health → `{"status":"UP"}`
- http://localhost:8081/api/wildlife/animals → JSON array of animals
- http://localhost:8084/api/incidents → JSON array of incidents
- http://localhost:8084/api/incidents/filter?zoneId=1&fromDate=2026-10-01&toDate=2026-10-31 → filtered results

---

## Service Health Checks

Verify all services are up:
- http://localhost:8081/api/wildlife/health
- http://localhost:8082/api/protected-areas/health
- http://localhost:8083/api/patrols/health
- http://localhost:8084/api/incidents/health

Each returns: `{"status":"UP","service":"<name>"}`

---

## Sample Data Reference

### Animals
| Tag ID | Name | Species | Health |
|--------|------|---------|--------|
| WL-001 | Raja | Bengal Tiger | Healthy |
| WL-002 | Maya | Asian Elephant | Healthy |
| WL-003 | Kali | Indian Rhino | Injured |
| WL-004 | Hima | Snow Leopard | Healthy |
| WL-005 | Jaya | Bengal Tiger | Critical |

### Protected Areas
| Code | Name | Type |
|------|------|------|
| PA-001 | Jim Corbett National Park | National Park |
| PA-002 | Ranthambore Tiger Reserve | Tiger Reserve |
| PA-003 | Kaziranga National Park | National Park |

### Staff
| ID | Name | Designation | Availability |
|----|------|-------------|--------------|
| ST-001 | Ravi Kumar | Forest Officer | Available |
| ST-002 | Priya Sharma | Ranger | Available |
| ST-003 | Amit Singh | Guard | On Patrol |
