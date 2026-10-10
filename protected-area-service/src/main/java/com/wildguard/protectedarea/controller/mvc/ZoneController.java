package com.wildguard.protectedarea.controller.mvc;

import com.wildguard.protectedarea.entity.ProtectedArea;
import com.wildguard.protectedarea.entity.Zone;
import com.wildguard.protectedarea.service.HabitatService;
import com.wildguard.protectedarea.service.ProtectedAreaService;
import com.wildguard.protectedarea.service.WaterSourceService;
import com.wildguard.protectedarea.service.ZoneService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/zones")
public class ZoneController {

    private final ZoneService service;
    private final ProtectedAreaService protectedAreaService;
    private final HabitatService habitatService;
    private final WaterSourceService waterSourceService;

    public ZoneController(ZoneService service, ProtectedAreaService protectedAreaService, HabitatService habitatService, WaterSourceService waterSourceService) {
        this.service = service;
        this.protectedAreaService = protectedAreaService;
        this.habitatService = habitatService;
        this.waterSourceService = waterSourceService;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("zones", service.findAll());
        return "zones/list";
    }

    @GetMapping("/new")
    public String createForm(@RequestParam(required = false) Long areaId, Model model) {
        Zone zone = new Zone();
        if (areaId != null) {
            zone.setProtectedAreaId(areaId);
            ProtectedArea pa = protectedAreaService.findById(areaId).orElse(null);
            if(pa != null) zone.setProtectedAreaName(pa.getName());
        }
        model.addAttribute("zone", zone);
        model.addAttribute("areas", protectedAreaService.findAll());
        return "zones/form";
    }

    @PostMapping
    public String save(@ModelAttribute Zone zone) {
        if(zone.getProtectedAreaId() != null) {
            ProtectedArea pa = protectedAreaService.findById(zone.getProtectedAreaId()).orElse(null);
            if(pa != null) zone.setProtectedAreaName(pa.getName());
        }
        service.save(zone);
        return "redirect:/zones";
    }

    @GetMapping("/{id}")
    public String detail(@PathVariable Long id, Model model) {
        Zone zone = service.findById(id).orElseThrow();
        model.addAttribute("zone", zone);
        model.addAttribute("habitats", habitatService.getByZone(id));
        model.addAttribute("waterSources", waterSourceService.getByZone(id));
        return "zones/detail";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("zone", service.findById(id).orElseThrow());
        model.addAttribute("areas", protectedAreaService.findAll());
        return "zones/form";
    }

    @PostMapping("/{id}/edit")
    public String update(@PathVariable Long id, @ModelAttribute Zone zone) {
        if(zone.getProtectedAreaId() != null) {
            ProtectedArea pa = protectedAreaService.findById(zone.getProtectedAreaId()).orElse(null);
            if(pa != null) zone.setProtectedAreaName(pa.getName());
        }
        zone.setId(id);
        service.save(zone);
        return "redirect:/zones/" + id;
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        service.deleteById(id);
        return "redirect:/zones";
    }
}
