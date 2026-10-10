package com.wildguard.incident.controller;

import com.wildguard.incident.entity.Incident;
import com.wildguard.incident.service.IncidentService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/incidents")
public class IncidentController {

    private final IncidentService service;

    public IncidentController(IncidentService service) {
        this.service = service;
    }

    @GetMapping
    public String list(
            @RequestParam(required = false) String zoneId,
            @RequestParam(required = false) String severity,
            @RequestParam(required = false) String type,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fromDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate toDate,
            Model model) {
        
        List<Incident> incidents = service.searchIncidents(zoneId, severity, type, status, fromDate, toDate);
        model.addAttribute("incidents", incidents);
        model.addAttribute("zoneId", zoneId);
        model.addAttribute("severity", severity);
        model.addAttribute("type", type);
        model.addAttribute("status", status);
        model.addAttribute("fromDate", fromDate);
        model.addAttribute("toDate", toDate);
        return "incidents/list";
    }

    @GetMapping("/new")
    public String newForm(Model model) {
        model.addAttribute("incident", new Incident());
        return "incidents/form";
    }

    @PostMapping
    public String save(@ModelAttribute Incident incident) {
        service.save(incident);
        return "redirect:/incidents";
    }

    @GetMapping("/{id}")
    public String detail(@PathVariable Long id, Model model) {
        Incident incident = service.getById(id).orElseThrow();
        model.addAttribute("incident", incident);
        return "incidents/detail";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        Incident incident = service.getById(id).orElseThrow();
        model.addAttribute("incident", incident);
        return "incidents/form";
    }

    @PostMapping("/{id}/edit")
    public String update(@PathVariable Long id, @ModelAttribute Incident incident) {
        incident.setId(id);
        service.save(incident);
        return "redirect:/incidents/" + id;
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "redirect:/incidents";
    }

    @PostMapping("/{id}/status")
    public String updateStatus(@PathVariable Long id, @RequestParam String status, @RequestParam(required = false) String responseNotes) {
        service.updateStatus(id, status, responseNotes);
        return "redirect:/incidents/" + id;
    }
    
    @PostMapping("/{id}/resolve")
    public String resolve(@PathVariable Long id, @RequestParam String resolutionDetails) {
        service.resolve(id, resolutionDetails);
        return "redirect:/incidents/" + id;
    }

    @GetMapping("/report")
    public String reportForm(
            @RequestParam(required = false) String zoneId,
            @RequestParam(required = false) String month,
            Model model) {
        
        if (month != null && !month.isEmpty()) {
            String[] parts = month.split("-");
            int y = Integer.parseInt(parts[0]);
            int m = Integer.parseInt(parts[1]);
            Long zId = (zoneId != null && !zoneId.isEmpty()) ? Long.parseLong(zoneId) : null;
            List<Incident> incidents = service.getIncidentsByZoneAndMonth(zId, y, m);
            model.addAttribute("incidents", incidents);
            
            long total = incidents.size();
            long low = incidents.stream().filter(i -> "LOW".equals(i.getSeverity())).count();
            long med = incidents.stream().filter(i -> "MEDIUM".equals(i.getSeverity())).count();
            long high = incidents.stream().filter(i -> "HIGH".equals(i.getSeverity())).count();
            long crit = incidents.stream().filter(i -> "CRITICAL".equals(i.getSeverity())).count();
            
            model.addAttribute("total", total);
            model.addAttribute("countLow", low);
            model.addAttribute("countMed", med);
            model.addAttribute("countHigh", high);
            model.addAttribute("countCrit", crit);
            model.addAttribute("showResults", true);
        } else {
            model.addAttribute("showResults", false);
        }
        
        model.addAttribute("zoneId", zoneId);
        model.addAttribute("month", month);
        return "incidents/report";
    }
}
