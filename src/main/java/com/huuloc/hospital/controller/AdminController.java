package com.huuloc.hospital.controller;

import com.huuloc.hospital.entity.*;
import com.huuloc.hospital.repository.EmployeeRepository;
import com.huuloc.hospital.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping(value = {"", "/", "/employees"})
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

    @GetMapping(value = "/employees/preview/{id}")
    public String previewEmployee(Model model, @PathVariable Long id) {
        Employee employee;
        try {
            employee = adminService
                    .getEmployee(id);
        } catch (IllegalArgumentException e) {
            model.addAttribute("errorMessage", e.getMessage());
            model.addAttribute("employees", adminService.getAllEmployees());
            return "admin/employees";
        }
        model.addAttribute("employee", employee);
        return "admin/view-employee";
    }

    @GetMapping(value = "/employees/new")
    public String newEmployee(Model model) {
        model.addAttribute("employee", new Employee());
        return "admin/new-employee";
    }

    @PostMapping(value = "/employees/save")
    public ModelAndView saveEmployee(@Valid Employee employee, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView(new RedirectView());
        if (adminService.checkEmployeeExists(employee)) {
            bindingResult
                    .rejectValue("username", "error.employee",
                            "User already exists");
        }


        modelAndView.setViewName("admin/new-employee");
        if (bindingResult.hasErrors()) {
            modelAndView.addObject("errorMessage",
                    bindingResult.getFieldError()
                            .getDefaultMessage());
        } else {
            employee.setJoinDate(new Date());
            employee.setPassword(bCryptPasswordEncoder.encode(employee.getPassword()));
            adminService.addEmployee(employee);
            modelAndView.addObject("successMessage",
                    "Employee has been added successfully");
        }
        return modelAndView;
    }

    @GetMapping(value = "/employees/edit/{id}")
    public String editEmployee(Model model, @PathVariable Long id) {
        Employee employee = adminService
                .getEmployee(id);
        model.addAttribute("employee", employee);
        return "admin/edit-employee";
    }

    @PostMapping(value = "/employees/update/{id}")
    public ModelAndView updateEmployee(@PathVariable Long id,
                                       @Valid Employee employee,
                                       BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView(new RedirectView());
        modelAndView.setViewName("admin/edit-employee");

        Employee oldEmployee = adminService.getEmployee(id);
        if (!oldEmployee.getPassword().equals(employee.getPassword())) {
            employee.setPassword(bCryptPasswordEncoder.encode(employee.getPassword()));
        }
        if (bindingResult.hasErrors()) {
            modelAndView.addObject("errorMessage",
                    bindingResult.getFieldError()
                            .getDefaultMessage());
        } else {
            adminService.updateEmployee(id, employee);
            modelAndView.addObject("successMessage",
                    "Employee has been updated successfully");
        }
        return modelAndView;
    }

    private ModelAndView EmployeeNotExistsModel(Long id, ModelAndView modelAndView) {
        if (!adminService.checkEmployeeExists(id)) {
            modelAndView.addObject("errorMessage",
                    "Employee not found");
            modelAndView.setViewName("admin/employees");
            return modelAndView;
        }
        return null;
    }

    @GetMapping(value = "/employees/delete/{id}")
    public ModelAndView deleteEmployee(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView(new RedirectView());
        ModelAndView modelAndView1 = EmployeeNotExistsModel(id, modelAndView);
        if (modelAndView1 != null) return modelAndView1;
        adminService.deleteEmployee(id);
        modelAndView.addObject("successMessage",
                "Employee has been deleted successfully");
        modelAndView.addObject("employees", adminService.getAllEmployees());
        modelAndView.setViewName("admin/employees");
        return modelAndView;
    }
}