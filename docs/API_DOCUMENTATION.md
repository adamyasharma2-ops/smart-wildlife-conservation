# API Documentation — WildGuard Wildlife Conservation System

All endpoints produce and consume JSON (`application/json`). Cross-service calls utilize Spring `RestClient`.

---

## 1. Wildlife Service (Port 8081)
Base URL: `http://localhost:8081/api/wildlife`

### Animals
| Method | Endpoint | Description |
|--------|----------|-------------|
| `GET` | `/animals` | List all registered animals |
| `GET` | `/animals/{id}` | Get animal by ID |
| `POST` | `/animals` | Register new animal record |
| `PUT` | `/animals/{id}` | Update animal record |
| `DELETE` | `/animals/{id}` | Delete animal record |

### Species
| Method | Endpoint | Description |
|--------|----------|-------------|
| `GET` | `/species` | List all species in catalogue |

### Stats & Health
| Method | Endpoint | Description |
|--------|----------|-------------|
| `GET` | `/stats` | Consolidated metrics across all services |
| `GET` | `/health` | Service health status |

---

## 2. Protected Area Service (Port 8082)
Base URL: `http://localhost:8082/api/protected-areas`

### Protected Areas & Zones
| Method | Endpoint | Description |
|--------|----------|-------------|
| `GET` | `/` | List all protected areas |
| `GET` | `/{id}` | Get protected area details |
| `POST` | `/` | Register protected area |
| `PUT` | `/{id}` | Update protected area |
| `DELETE`| `/{id}` | Delete protected area |
| `GET` | `/{id}/zones` | List zones for protected area |
| `GET` | `/zones` | List all zones |
| `GET` | `/zones/{id}` | Get zone details |
| `POST` | `/zones` | Create zone |
| `PUT` | `/zones/{id}` | Update zone |
| `GET` | `/{id}/habitats` | List habitats in area |
| `GET` | `/{id}/water-sources` | List water sources in area |
| `GET` | `/stats` | Counts of areas, zones, habitats, water sources |
| `GET` | `/health` | Health status |

---

## 3. Patrol Service (Port 8083)
Base URL: `http://localhost:8083/api/patrols`

### Patrol Operations
| Method | Endpoint | Description |
|--------|----------|-------------|
| `GET` | `/` | List all patrols |
| `GET` | `/{id}` | Get patrol details with assignments |
| `POST` | `/` | Schedule new patrol |
| `POST` | `/{id}/start` | Mark patrol started (`IN_PROGRESS`) |
| `POST` | `/{id}/complete` | Complete patrol with observations & outcome (releases resources) |
| `GET` | `/stats` | Active, planned, completed patrol counts |
| `GET` | `/staff/available`| List currently available field staff |
| `GET` | `/health` | Health status |

---

## 4. Incident Service (Port 8084)
Base URL: `http://localhost:8084/api/incidents`

### Incidents
| Method | Endpoint | Description |
|--------|----------|-------------|
| `GET` | `/` | List all incidents |
| `GET` | `/{id}` | Get incident details |
| `POST` | `/` | Report new incident |
| `PUT` | `/{id}` | Update incident |
| `DELETE`| `/{id}` | Delete incident |
| `POST` | `/{id}/status` | Update incident status & response notes |
| `POST` | `/{id}/resolve`| Resolve incident with resolution details |
| `GET` | `/filter?zoneId=&fromDate=&toDate=&severity=&status=` | Query incidents by zone & month (Official illustrative scenario) |
| `GET` | `/stats` | Incident statistics by severity and status |

### Rescues & Programmes
Base URLs: `http://localhost:8084/api/rescues` & `http://localhost:8084/api/conservation-programmes`
| Method | Endpoint | Description |
|--------|----------|-------------|
| `GET` | `/api/rescues` | List all rescue cases |
| `POST` | `/api/rescues/{id}/status` | Update rescue status |
| `POST` | `/api/rescues/{id}/release`| Record rehabilitation release |
| `GET` | `/api/conservation-programmes` | List conservation programmes |
| `POST` | `/api/conservation-programmes/{id}/activities` | Add milestone activity |
| `PUT` | `/api/conservation-programmes/activities/{id}` | Update progress % (auto-recalculates programme completion) |
| `GET` | `/api/incidents/health` | Health status |
