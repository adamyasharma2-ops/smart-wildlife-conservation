package com.wildguard.patrol.controller;

import com.wildguard.patrol.entity.Equipment;
import com.wildguard.patrol.entity.Vehicle;
import com.wildguard.patrol.service.EquipmentService;
import com.wildguard.patrol.service.VehicleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/resources")
public class ResourceController {
    private final VehicleService vehicleService;
    private final EquipmentService equipmentService;

    public ResourceController(VehicleService vehicleService, EquipmentService equipmentService) {
        this.vehicleService = vehicleService;
        this.equipmentService = equipmentService;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("vehicles", vehicleService.findAll());
        model.addAttribute("equipment", equipmentService.findAll());
        return "resources/list";
    }

    @GetMapping("/vehicles/new")
    public String newVehicle(Model model) {
        model.addAttribute("vehicle", new Vehicle());
        return "resources/vehicle-form";
    }

    @PostMapping("/vehicles")
    public String saveVehicle(@ModelAttribute Vehicle vehicle) {
        if (vehicle.getStatus() == null) vehicle.setStatus("AVAILABLE");
        vehicleService.save(vehicle);
        return "redirect:/resources";
    }

    @GetMapping("/equipment/new")
    public String newEquipment(Model model) {
        model.addAttribute("equipment", new Equipment());
        return "resources/equipment-form";
    }

    @PostMapping("/equipment")
    public String saveEquipment(@ModelAttribute Equipment equipment) {
        if (equipment.getStatus() == null) equipment.setStatus("AVAILABLE");
        equipmentService.save(equipment);
        return "redirect:/resources";
    }
}
