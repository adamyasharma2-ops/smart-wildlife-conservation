package com.wildguard.protectedarea.controller.mvc;

import com.wildguard.protectedarea.entity.ProtectedArea;
import com.wildguard.protectedarea.service.HabitatService;
import com.wildguard.protectedarea.service.ProtectedAreaService;
import com.wildguard.protectedarea.service.WaterSourceService;
import com.wildguard.protectedarea.service.ZoneService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/areas")
public class ProtectedAreaController {

    private final ProtectedAreaService service;
    private final ZoneService zoneService;
    private final HabitatService habitatService;
    private final WaterSourceService waterSourceService;

    public ProtectedAreaController(ProtectedAreaService service, ZoneService zoneService, HabitatService habitatService, WaterSourceService waterSourceService) {
        this.service = service;
        this.zoneService = zoneService;
        this.habitatService = habitatService;
        this.waterSourceService = waterSourceService;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("areas", service.findAll());
        return "areas/list";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("area", new ProtectedArea());
        return "areas/form";
    }

    @PostMapping
    public String save(@ModelAttribute ProtectedArea area) {
        service.save(area);
        return "redirect:/areas";
    }

    @GetMapping("/{id}")
    public String detail(@PathVariable Long id, Model model) {
        ProtectedArea area = service.findById(id).orElseThrow();
        model.addAttribute("area", area);
        model.addAttribute("zones", zoneService.getByProtectedArea(id));
        model.addAttribute("habitats", habitatService.getByProtectedArea(id));
        model.addAttribute("waterSources", waterSourceService.getByProtectedArea(id));
        return "areas/detail";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("area", service.findById(id).orElseThrow());
        return "areas/form";
    }

    @PostMapping("/{id}/edit")
    public String update(@PathVariable Long id, @ModelAttribute ProtectedArea area) {
        area.setId(id);
        service.save(area);
        return "redirect:/areas/" + id;
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        service.deleteById(id);
        return "redirect:/areas";
    }
}
