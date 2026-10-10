package com.wildguard.incident.controller;

import com.wildguard.incident.entity.RescueCase;
import com.wildguard.incident.service.RescueCaseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/rescues")
public class RescueRestController {

    private final RescueCaseService service;

    public RescueRestController(RescueCaseService service) {
        this.service = service;
    }

    @GetMapping
    public List<RescueCase> getAll() {
        return service.getAll();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<RescueCase> getById(@PathVariable Long id) {
        return service.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public RescueCase create(@RequestBody RescueCase rescueCase) {
        return service.save(rescueCase);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<RescueCase> update(@PathVariable Long id, @RequestBody RescueCase rescueCase) {
        if (service.getById(id).isEmpty()) return ResponseEntity.notFound().build();
        rescueCase.setId(id);
        return ResponseEntity.ok(service.save(rescueCase));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (service.getById(id).isEmpty()) return ResponseEntity.notFound().build();
        service.delete(id);
        return ResponseEntity.ok().build();
    }
    
    @PostMapping("/{id}/status")
    public ResponseEntity<RescueCase> updateStatus(@PathVariable Long id, @RequestBody Map<String, String> payload) {
        try {
            return ResponseEntity.ok(service.updateStatus(id, payload.get("status")));
        } catch(Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @PostMapping("/{id}/transfer")
    public ResponseEntity<RescueCase> transfer(@PathVariable Long id, @RequestBody Map<String, String> payload) {
        try {
            return ResponseEntity.ok(service.recordTransfer(id, payload.get("location"), LocalDateTime.now()));
        } catch(Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @PostMapping("/{id}/release")
    public ResponseEntity<RescueCase> release(@PathVariable Long id, @RequestBody Map<String, String> payload) {
        try {
            return ResponseEntity.ok(service.recordRelease(id, payload.get("location"), LocalDateTime.now(), payload.get("outcome")));
        } catch(Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
