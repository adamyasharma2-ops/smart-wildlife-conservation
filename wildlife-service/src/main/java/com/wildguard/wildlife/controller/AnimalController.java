package com.wildguard.wildlife.controller;

import com.wildguard.wildlife.entity.Animal;
import com.wildguard.wildlife.entity.AnimalObservation;
import com.wildguard.wildlife.entity.Species;
import com.wildguard.wildlife.service.AnimalObservationService;
import com.wildguard.wildlife.service.AnimalService;
import com.wildguard.wildlife.service.SpeciesService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/animals")
public class AnimalController {
    private final AnimalService animalService;
    private final SpeciesService speciesService;
    private final AnimalObservationService observationService;

    public AnimalController(AnimalService animalService, SpeciesService speciesService, AnimalObservationService observationService) {
        this.animalService = animalService;
        this.speciesService = speciesService;
        this.observationService = observationService;
    }

    @GetMapping
    public String list(@RequestParam(required = false) String q, Model model) {
        if (q != null && !q.trim().isEmpty()) {
            model.addAttribute("animals", animalService.search(q));
            model.addAttribute("query", q);
        } else {
            model.addAttribute("animals", animalService.findAll());
        }
        return "animals/list";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("animal", new Animal());
        model.addAttribute("speciesList", speciesService.findAll());
        return "animals/form";
    }

    @PostMapping
    public String save(@ModelAttribute Animal animal) {
        if (animal.getSpeciesId() != null) {
            speciesService.findById(animal.getSpeciesId()).ifPresent(s -> {
                animal.setSpeciesName(s.getCommonName());
                if (animal.getConservationStatus() == null || animal.getConservationStatus().isEmpty()) {
                    animal.setConservationStatus(s.getConservationStatus());
                }
            });
        }
        animalService.save(animal);
        return "redirect:/animals";
    }

    @GetMapping("/{id}")
    public String detail(@PathVariable Long id, Model model) {
        Animal animal = animalService.findById(id).orElseThrow();
        model.addAttribute("animal", animal);
        model.addAttribute("observations", observationService.getObservationsForAnimal(id));
        AnimalObservation newObs = new AnimalObservation();
        newObs.setAnimalId(id);
        newObs.setAnimalTagId(animal.getTagId());
        model.addAttribute("newObservation", newObs);
        return "animals/detail";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("animal", animalService.findById(id).orElseThrow());
        model.addAttribute("speciesList", speciesService.findAll());
        return "animals/form";
    }

    @PostMapping("/{id}/edit")
    public String update(@PathVariable Long id, @ModelAttribute Animal animal) {
        if (animal.getSpeciesId() != null) {
            speciesService.findById(animal.getSpeciesId()).ifPresent(s -> animal.setSpeciesName(s.getCommonName()));
        }
        animal.setId(id);
        animalService.save(animal);
        return "redirect:/animals/" + id;
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        animalService.deleteById(id);
        return "redirect:/animals";
    }

    @PostMapping("/{id}/observations")
    public String addObservation(@PathVariable Long id, @ModelAttribute AnimalObservation observation) {
        observation.setAnimalId(id);
        animalService.findById(id).ifPresent(a -> observation.setAnimalTagId(a.getTagId()));
        observationService.save(observation);
        return "redirect:/animals/" + id;
    }
}
