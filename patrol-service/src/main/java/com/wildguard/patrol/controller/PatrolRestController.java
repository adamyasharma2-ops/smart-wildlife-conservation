package com.wildguard.patrol.controller;

import com.wildguard.patrol.entity.Patrol;
import com.wildguard.patrol.entity.Staff;
import com.wildguard.patrol.service.PatrolService;
import com.wildguard.patrol.service.StaffService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/patrols")
public class PatrolRestController {
    private final PatrolService patrolService;
    private final StaffService staffService;

    public PatrolRestController(PatrolService patrolService, StaffService staffService) {
        this.patrolService = patrolService;
        this.staffService = staffService;
    }

    @GetMapping
    public List<Patrol> getAll() { return patrolService.findAll(); }

    @GetMapping("/{id}")
    public ResponseEntity<Patrol> getById(@PathVariable Long id) {
        return patrolService.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Patrol create(@RequestBody Patrol patrol) { return patrolService.save(patrol); }

    @PostMapping("/{id}/start")
    public ResponseEntity<Void> start(@PathVariable Long id) {
        patrolService.startPatrol(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/complete")
    public ResponseEntity<Void> complete(@PathVariable Long id, @RequestBody Map<String, String> body) {
        patrolService.completePatrol(id, body.get("observations"), body.get("outcome"));
        return ResponseEntity.ok().build();
    }

    @GetMapping("/stats")
    public Map<String, Object> getStats() { return patrolService.getStats(); }

    @GetMapping("/staff/available")
    public List<Staff> getAvailableStaff() { return staffService.getAvailableStaff(); }

    @GetMapping("/health")
    public Map<String, String> health() { return Map.of("status", "UP", "service", "patrol-service"); }
}
