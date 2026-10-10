package com.wildguard.protectedarea.controller.rest;

import com.wildguard.protectedarea.entity.Habitat;
import com.wildguard.protectedarea.entity.ProtectedArea;
import com.wildguard.protectedarea.entity.WaterSource;
import com.wildguard.protectedarea.entity.Zone;
import com.wildguard.protectedarea.service.HabitatService;
import com.wildguard.protectedarea.service.ProtectedAreaService;
import com.wildguard.protectedarea.service.WaterSourceService;
import com.wildguard.protectedarea.service.ZoneService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/protected-areas")
public class ProtectedAreaRestController {

    private final ProtectedAreaService protectedAreaService;
    private final ZoneService zoneService;
    private final HabitatService habitatService;
    private final WaterSourceService waterSourceService;

    public ProtectedAreaRestController(ProtectedAreaService protectedAreaService, ZoneService zoneService, HabitatService habitatService, WaterSourceService waterSourceService) {
        this.protectedAreaService = protectedAreaService;
        this.zoneService = zoneService;
        this.habitatService = habitatService;
        this.waterSourceService = waterSourceService;
    }

    @GetMapping
    public List<ProtectedArea> listAll() { return protectedAreaService.findAll(); }

    @GetMapping("/{id}")
    public ResponseEntity<ProtectedArea> getArea(@PathVariable Long id) {
        return protectedAreaService.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ProtectedArea createArea(@RequestBody ProtectedArea area) {
        return protectedAreaService.save(area);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProtectedArea> updateArea(@PathVariable Long id, @RequestBody ProtectedArea area) {
        return protectedAreaService.findById(id).map(existing -> {
            area.setId(existing.getId());
            return ResponseEntity.ok(protectedAreaService.save(area));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteArea(@PathVariable Long id) {
        protectedAreaService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}/zones")
    public List<Zone> getZonesForArea(@PathVariable Long id) { return zoneService.getByProtectedArea(id); }

    @GetMapping("/zones")
    public List<Zone> listAllZones() { return zoneService.findAll(); }

    @GetMapping("/zones/{id}")
    public ResponseEntity<Zone> getZone(@PathVariable Long id) {
        return zoneService.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/zones")
    public Zone createZone(@RequestBody Zone zone) { return zoneService.save(zone); }

    @PutMapping("/zones/{id}")
    public ResponseEntity<Zone> updateZone(@PathVariable Long id, @RequestBody Zone zone) {
        return zoneService.findById(id).map(existing -> {
            zone.setId(existing.getId());
            return ResponseEntity.ok(zoneService.save(zone));
        }).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}/habitats")
    public List<Habitat> getHabitatsForArea(@PathVariable Long id) { return habitatService.getByProtectedArea(id); }

    @GetMapping("/{id}/water-sources")
    public List<WaterSource> getWaterSourcesForArea(@PathVariable Long id) { return waterSourceService.getByProtectedArea(id); }

    @GetMapping("/stats")
    public Map<String, Long> getStats() {
        Map<String, Long> stats = new HashMap<>();
        stats.put("areas", (long) protectedAreaService.findAll().size());
        stats.put("zones", (long) zoneService.findAll().size());
        stats.put("habitats", (long) habitatService.findAll().size());
        stats.put("waterSources", (long) waterSourceService.findAll().size());
        return stats;
    }

    @GetMapping("/health")
    public Map<String, String> health() {
        return Map.of("status", "UP");
    }
}
