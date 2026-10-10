package com.wildguard.patrol.controller;

import com.wildguard.patrol.entity.Staff;
import com.wildguard.patrol.service.StaffService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/staff")
public class StaffController {
    private final StaffService staffService;
    public StaffController(StaffService staffService) { this.staffService = staffService; }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("staffList", staffService.findAll());
        return "staff/list";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("staff", new Staff());
        return "staff/form";
    }

    @PostMapping
    public String save(@ModelAttribute Staff staff) {
        if (staff.getAvailability() == null) staff.setAvailability("AVAILABLE");
        staffService.save(staff);
        return "redirect:/staff";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("staff", staffService.findById(id).orElseThrow());
        return "staff/form";
    }

    @PostMapping("/{id}/edit")
    public String update(@PathVariable Long id, @ModelAttribute Staff staff) {
        staff.setId(id);
        staffService.save(staff);
        return "redirect:/staff";
    }
}
