package com.wildguard.patrol.controller;

import com.wildguard.patrol.entity.Patrol;
import com.wildguard.patrol.service.EquipmentService;
import com.wildguard.patrol.service.PatrolService;
import com.wildguard.patrol.service.StaffService;
import com.wildguard.patrol.service.VehicleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/patrols")
public class PatrolController {
    private final PatrolService patrolService;
    private final StaffService staffService;
    private final VehicleService vehicleService;
    private final EquipmentService equipmentService;

    public PatrolController(PatrolService patrolService, StaffService staffService,
                            VehicleService vehicleService, EquipmentService equipmentService) {
        this.patrolService = patrolService;
        this.staffService = staffService;
        this.vehicleService = vehicleService;
        this.equipmentService = equipmentService;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("patrols", patrolService.findAll());
        return "patrols/list";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("patrol", new Patrol());
        return "patrols/form";
    }

    @PostMapping
    public String save(@ModelAttribute Patrol patrol) {
        patrolService.save(patrol);
        return "redirect:/patrols";
    }

    @GetMapping("/{id}")
    public String detail(@PathVariable Long id, Model model) {
        Patrol patrol = patrolService.findById(id).orElseThrow();
        model.addAttribute("patrol", patrol);
        model.addAttribute("staffAssignments", patrolService.getStaffAssignments(id));
        model.addAttribute("resourceAssignments", patrolService.getResourceAssignments(id));
        model.addAttribute("availableStaff", staffService.getAvailableStaff());
        model.addAttribute("availableVehicles", vehicleService.getAvailableVehicles());
        model.addAttribute("availableEquipment", equipmentService.getAvailableEquipment());
        return "patrols/detail";
    }

    @PostMapping("/{id}/start")
    public String start(@PathVariable Long id) {
        patrolService.startPatrol(id);
        return "redirect:/patrols/" + id;
    }

    @PostMapping("/{id}/complete")
    public String complete(@PathVariable Long id, @RequestParam String observations, @RequestParam String outcome) {
        patrolService.completePatrol(id, observations, outcome);
        return "redirect:/patrols/" + id;
    }

    @PostMapping("/{id}/assign-staff")
    public String assignStaff(@PathVariable Long id, @RequestParam Long staffId, @RequestParam(required = false) String role) {
        patrolService.assignStaff(id, staffId, role);
        return "redirect:/patrols/" + id;
    }

    @PostMapping("/{id}/assign-vehicle")
    public String assignVehicle(@PathVariable Long id, @RequestParam Long vehicleId) {
        patrolService.assignVehicle(id, vehicleId);
        return "redirect:/patrols/" + id;
    }

    @PostMapping("/{id}/assign-equipment")
    public String assignEquipment(@PathVariable Long id, @RequestParam Long equipmentId) {
        patrolService.assignEquipment(id, equipmentId);
        return "redirect:/patrols/" + id;
    }
}
