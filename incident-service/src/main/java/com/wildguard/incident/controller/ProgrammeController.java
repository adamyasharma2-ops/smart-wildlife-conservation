package com.wildguard.incident.controller;

import com.wildguard.incident.entity.ConservationProgramme;
import com.wildguard.incident.entity.ProgrammeActivity;
import com.wildguard.incident.service.ConservationProgrammeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/programmes")
public class ProgrammeController {

    private final ConservationProgrammeService service;

    public ProgrammeController(ConservationProgrammeService service) {
        this.service = service;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("programmes", service.getAll());
        return "programmes/list";
    }

    @GetMapping("/new")
    public String newForm(Model model) {
        model.addAttribute("programme", new ConservationProgramme());
        return "programmes/form";
    }

    @PostMapping
    public String save(@ModelAttribute ConservationProgramme programme) {
        service.save(programme);
        return "redirect:/programmes";
    }

    @GetMapping("/{id}")
    public String detail(@PathVariable Long id, Model model) {
        model.addAttribute("programme", service.getById(id).orElseThrow());
        model.addAttribute("activities", service.getActivitiesByProgrammeId(id));
        model.addAttribute("newActivity", new ProgrammeActivity());
        return "programmes/detail";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("programme", service.getById(id).orElseThrow());
        return "programmes/form";
    }

    @PostMapping("/{id}/edit")
    public String update(@PathVariable Long id, @ModelAttribute ConservationProgramme programme) {
        programme.setId(id);
        service.save(programme);
        return "redirect:/programmes/" + id;
    }

    @PostMapping("/{id}/activities")
    public String addActivity(@PathVariable Long id, @ModelAttribute ProgrammeActivity activity) {
        service.addActivity(id, activity);
        return "redirect:/programmes/" + id;
    }

    @PostMapping("/activities/{actId}/status")
    public String updateActivityStatus(@PathVariable Long actId, @RequestParam Long programmeId, @RequestParam String status, @RequestParam int progressPercent) {
        service.updateActivityStatus(actId, status, progressPercent);
        return "redirect:/programmes/" + programmeId;
    }
}
