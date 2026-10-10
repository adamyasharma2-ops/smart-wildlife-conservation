package com.wildguard.incident.controller;

import com.wildguard.incident.entity.RescueCase;
import com.wildguard.incident.service.RescueCaseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/rescues")
public class RescueController {

    private final RescueCaseService service;

    public RescueController(RescueCaseService service) {
        this.service = service;
    }

    @GetMapping
    public String list(@RequestParam(required = false) String status, Model model) {
        List<RescueCase> cases = service.getAll();
        if (status != null && !status.isEmpty()) {
            cases = cases.stream().filter(c -> status.equals(c.getStatus())).collect(Collectors.toList());
        }
        model.addAttribute("cases", cases);
        model.addAttribute("status", status);
        return "rescues/list";
    }

    @GetMapping("/new")
    public String newForm(Model model) {
        model.addAttribute("rescueCase", new RescueCase());
        return "rescues/form";
    }

    @PostMapping
    public String save(@ModelAttribute RescueCase rescueCase) {
        service.save(rescueCase);
        return "redirect:/rescues";
    }

    @GetMapping("/{id}")
    public String detail(@PathVariable Long id, Model model) {
        model.addAttribute("rescueCase", service.getById(id).orElseThrow());
        return "rescues/detail";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("rescueCase", service.getById(id).orElseThrow());
        return "rescues/form";
    }

    @PostMapping("/{id}/edit")
    public String update(@PathVariable Long id, @ModelAttribute RescueCase rescueCase) {
        rescueCase.setId(id);
        service.save(rescueCase);
        return "redirect:/rescues/" + id;
    }

    @PostMapping("/{id}/status")
    public String updateStatus(@PathVariable Long id, @RequestParam String status) {
        service.updateStatus(id, status);
        return "redirect:/rescues/" + id;
    }

    @PostMapping("/{id}/transfer")
    public String transfer(@PathVariable Long id, @RequestParam String transferLocation, @RequestParam(required = false) String transferDateTime) {
        LocalDateTime dt = (transferDateTime != null && !transferDateTime.isEmpty()) ? LocalDateTime.parse(transferDateTime) : LocalDateTime.now();
        service.recordTransfer(id, transferLocation, dt);
        return "redirect:/rescues/" + id;
    }

    @PostMapping("/{id}/release")
    public String release(@PathVariable Long id, @RequestParam String releaseLocation, @RequestParam(required = false) String releaseDateTime, @RequestParam String outcome) {
        LocalDateTime dt = (releaseDateTime != null && !releaseDateTime.isEmpty()) ? LocalDateTime.parse(releaseDateTime) : LocalDateTime.now();
        service.recordRelease(id, releaseLocation, dt, outcome);
        return "redirect:/rescues/" + id;
    }
}
