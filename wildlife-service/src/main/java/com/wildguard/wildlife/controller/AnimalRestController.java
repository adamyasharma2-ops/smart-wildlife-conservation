package com.wildguard.wildlife.controller;

import com.wildguard.wildlife.entity.Animal;
import com.wildguard.wildlife.entity.Species;
import com.wildguard.wildlife.service.AnimalService;
import com.wildguard.wildlife.service.DashboardService;
import com.wildguard.wildlife.service.SpeciesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/wildlife")
public class AnimalRestController {
    private final AnimalService animalService;
    private final SpeciesService speciesService;
    private final DashboardService dashboardService;

    public AnimalRestController(AnimalService animalService, SpeciesService speciesService, DashboardService dashboardService) {
        this.animalService = animalService;
        this.speciesService = speciesService;
        this.dashboardService = dashboardService;
    }

    @GetMapping("/animals")
    public List<Animal> getAllAnimals() { return animalService.findAll(); }

    @GetMapping("/animals/{id}")
    public ResponseEntity<Animal> getAnimal(@PathVariable Long id) {
        return animalService.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/animals")
    public Animal createAnimal(@RequestBody Animal animal) { return animalService.save(animal); }

    @PutMapping("/animals/{id}")
    public ResponseEntity<Animal> updateAnimal(@PathVariable Long id, @RequestBody Animal animal) {
        return animalService.findById(id).map(existing -> {
            animal.setId(existing.getId());
            return ResponseEntity.ok(animalService.save(animal));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/animals/{id}")
    public ResponseEntity<Void> deleteAnimal(@PathVariable Long id) {
        animalService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/species")
    public List<Species> getAllSpecies() { return speciesService.findAll(); }

    @GetMapping("/stats")
    public Map<String, Object> getStats() { return dashboardService.getDashboardData(); }

    @GetMapping("/health")
    public Map<String, String> health() { return Map.of("status", "UP", "service", "wildlife-service"); }
}
