package com.huuloc.hospital.controller;

import com.huuloc.hospital.entity.Employee;
import com.huuloc.hospital.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    private final EmployeeService employeeService;
    @Autowired
    public HomeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
    @GetMapping(value = "/")
    public String home(Model model, Authentication authentication) {
        if (authentication != null) {
            String role = authentication.getAuthorities().stream()
                    .findFirst().orElseThrow(
                            () ->
                                    new IllegalStateException("Cannot find role!"))
                    .getAuthority();
            switch (role) {
                case "ROLE_ADMIN" -> {
                    return "redirect:/admin";
                }
                case "ROLE_DOCTOR" -> {
                    return "redirect:/doctor";
                }
                case "ROLE_PHARMACIST" -> {
                    return "redirect:/pharmacist";
                }
                case "ROLE_ACCOUNTANT" -> {
                    return "redirect:/accountant";
                }
                case "ROLE_RECEPTIONIST" -> {
                    return "redirect:/receptionist";
                }
            }
        }
        return "index";
    }
}
