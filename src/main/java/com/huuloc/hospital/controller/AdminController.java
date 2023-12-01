package com.huuloc.hospital.controller;

import com.huuloc.hospital.entity.Employee;
import com.huuloc.hospital.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;
    @GetMapping(value = {"", "/employees"})
    public String home(Model model, Authentication authentication) {
        assert authentication != null;
        List<Employee> employees = adminService.getAllEmployees();
        model.addAttribute("employees", employees);
        return "admin/employees";
    }

    @GetMapping(value = "/emoployees/add")
    public String addEmployee(Model model) {
        model.addAttribute("employee", new Employee());
        return "admin/addEmployee";
    }
}
