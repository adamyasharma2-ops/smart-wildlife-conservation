package com.wildguard.protectedarea.controller.mvc;

import com.wildguard.protectedarea.entity.Habitat;
import com.wildguard.protectedarea.entity.ProtectedArea;
import com.wildguard.protectedarea.entity.Zone;
import com.wildguard.protectedarea.service.HabitatService;
import com.wildguard.protectedarea.service.ProtectedAreaService;
import com.wildguard.protectedarea.service.ZoneService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/habitats")
public class HabitatController {

    private final HabitatService service;
    private final ZoneService zoneService;
    private final ProtectedAreaService protectedAreaService;

    public HabitatController(HabitatService service, ZoneService zoneService, ProtectedAreaService protectedAreaService) {
        this.service = service;
        this.zoneService = zoneService;
        this.protectedAreaService = protectedAreaService;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("habitats", service.findAll());
        return "habitats/list";
    }

    @GetMapping("/new")
    public String createForm(@RequestParam(required = false) Long zoneId, Model model) {
        Habitat habitat = new Habitat();
        if (zoneId != null) {
            habitat.setZoneId(zoneId);
            Zone zone = zoneService.findById(zoneId).orElse(null);
            if(zone != null) {
                habitat.setZoneName(zone.getName());
                habitat.setProtectedAreaId(zone.getProtectedAreaId());
                habitat.setProtectedAreaName(zone.getProtectedAreaName());
            }
        }
        model.addAttribute("habitat", habitat);
        model.addAttribute("zones", zoneService.findAll());
        return "habitats/form";
    }

    @PostMapping
    public String save(@ModelAttribute Habitat habitat) {
        if(habitat.getZoneId() != null) {
            Zone zone = zoneService.findById(habitat.getZoneId()).orElse(null);
            if(zone != null) {
                habitat.setZoneName(zone.getName());
                habitat.setProtectedAreaId(zone.getProtectedAreaId());
                habitat.setProtectedAreaName(zone.getProtectedAreaName());
            }
        }
        service.save(habitat);
        return "redirect:/habitats";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("habitat", service.findById(id).orElseThrow());
        model.addAttribute("zones", zoneService.findAll());
        return "habitats/form";
    }

    @PostMapping("/{id}/edit")
    public String update(@PathVariable Long id, @ModelAttribute Habitat habitat) {
        if(habitat.getZoneId() != null) {
            Zone zone = zoneService.findById(habitat.getZoneId()).orElse(null);
            if(zone != null) {
                habitat.setZoneName(zone.getName());
                habitat.setProtectedAreaId(zone.getProtectedAreaId());
                habitat.setProtectedAreaName(zone.getProtectedAreaName());
            }
        }
        habitat.setId(id);
        service.save(habitat);
        return "redirect:/habitats";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        service.deleteById(id);
        return "redirect:/habitats";
    }
}
