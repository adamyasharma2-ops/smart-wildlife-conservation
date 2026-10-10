package com.wildguard.protectedarea.controller.mvc;

import com.wildguard.protectedarea.entity.WaterSource;
import com.wildguard.protectedarea.entity.Zone;
import com.wildguard.protectedarea.service.WaterSourceService;
import com.wildguard.protectedarea.service.ZoneService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/water-sources")
public class WaterSourceController {

    private final WaterSourceService service;
    private final ZoneService zoneService;

    public WaterSourceController(WaterSourceService service, ZoneService zoneService) {
        this.service = service;
        this.zoneService = zoneService;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("waterSources", service.findAll());
        return "watersources/list";
    }

    @GetMapping("/new")
    public String createForm(@RequestParam(required = false) Long zoneId, Model model) {
        WaterSource ws = new WaterSource();
        if (zoneId != null) {
            ws.setZoneId(zoneId);
            Zone zone = zoneService.findById(zoneId).orElse(null);
            if(zone != null) {
                ws.setZoneName(zone.getName());
                ws.setProtectedAreaId(zone.getProtectedAreaId());
                ws.setProtectedAreaName(zone.getProtectedAreaName());
            }
        }
        model.addAttribute("waterSource", ws);
        model.addAttribute("zones", zoneService.findAll());
        return "watersources/form";
    }

    @PostMapping
    public String save(@ModelAttribute WaterSource ws) {
        if(ws.getZoneId() != null) {
            Zone zone = zoneService.findById(ws.getZoneId()).orElse(null);
            if(zone != null) {
                ws.setZoneName(zone.getName());
                ws.setProtectedAreaId(zone.getProtectedAreaId());
                ws.setProtectedAreaName(zone.getProtectedAreaName());
            }
        }
        service.save(ws);
        return "redirect:/water-sources";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("waterSource", service.findById(id).orElseThrow());
        model.addAttribute("zones", zoneService.findAll());
        return "watersources/form";
    }

    @PostMapping("/{id}/edit")
    public String update(@PathVariable Long id, @ModelAttribute WaterSource ws) {
        if(ws.getZoneId() != null) {
            Zone zone = zoneService.findById(ws.getZoneId()).orElse(null);
            if(zone != null) {
                ws.setZoneName(zone.getName());
                ws.setProtectedAreaId(zone.getProtectedAreaId());
                ws.setProtectedAreaName(zone.getProtectedAreaName());
            }
        }
        ws.setId(id);
        service.save(ws);
        return "redirect:/water-sources";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        service.deleteById(id);
        return "redirect:/water-sources";
    }
}
