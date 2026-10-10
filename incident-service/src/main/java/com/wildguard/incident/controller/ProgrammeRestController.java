package com.wildguard.incident.controller;

import com.wildguard.incident.entity.ConservationProgramme;
import com.wildguard.incident.entity.ProgrammeActivity;
import com.wildguard.incident.service.ConservationProgrammeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/conservation-programmes")
public class ProgrammeRestController {

    private final ConservationProgrammeService service;

    public ProgrammeRestController(ConservationProgrammeService service) {
        this.service = service;
    }

    @GetMapping
    public List<ConservationProgramme> getAll() {
        return service.getAll();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ConservationProgramme> getById(@PathVariable Long id) {
        return service.getById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public ConservationProgramme create(@RequestBody ConservationProgramme programme) {
        return service.save(programme);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<ConservationProgramme> update(@PathVariable Long id, @RequestBody ConservationProgramme programme) {
        if(service.getById(id).isEmpty()) return ResponseEntity.notFound().build();
        programme.setId(id);
        return ResponseEntity.ok(service.save(programme));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if(service.getById(id).isEmpty()) return ResponseEntity.notFound().build();
        service.delete(id);
        return ResponseEntity.ok().build();
    }
    
    @GetMapping("/{id}/activities")
    public List<ProgrammeActivity> getActivities(@PathVariable Long id) {
        return service.getActivitiesByProgrammeId(id);
    }
    
    @PostMapping("/{id}/activities")
    public ProgrammeActivity addActivity(@PathVariable Long id, @RequestBody ProgrammeActivity activity) {
        return service.addActivity(id, activity);
    }
    
    @PutMapping("/activities/{actId}")
    public ProgrammeActivity updateActivity(@PathVariable Long actId, @RequestBody ProgrammeActivity activity) {
        return service.updateActivity(actId, activity);
    }
}
