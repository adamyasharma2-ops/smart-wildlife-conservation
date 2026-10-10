package com.wildguard.wildlife.controller;

import com.wildguard.wildlife.entity.Species;
import com.wildguard.wildlife.service.AnimalService;
import com.wildguard.wildlife.service.SpeciesService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/species")
public class SpeciesController {
    private final SpeciesService speciesService;
    private final AnimalService animalService;

    public SpeciesController(SpeciesService speciesService, AnimalService animalService) {
        this.speciesService = speciesService;
        this.animalService = animalService;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("speciesList", speciesService.findAll());
        return "species/list";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("species", new Species());
        return "species/form";
    }

    @PostMapping
    public String save(@ModelAttribute Species species) {
        speciesService.save(species);
        return "redirect:/species";
    }

    @GetMapping("/{id}")
    public String detail(@PathVariable Long id, Model model) {
        Species species = speciesService.findById(id).orElseThrow();
        model.addAttribute("species", species);
        model.addAttribute("animals", animalService.findBySpeciesId(id));
        return "species/detail";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("species", speciesService.findById(id).orElseThrow());
        return "species/form";
    }

    @PostMapping("/{id}/edit")
    public String update(@PathVariable Long id, @ModelAttribute Species species) {
        species.setId(id);
        speciesService.save(species);
        return "redirect:/species/" + id;
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        speciesService.deleteById(id);
        return "redirect:/species";
    }
}
