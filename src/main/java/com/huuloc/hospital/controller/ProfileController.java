package com.huuloc.hospital.controller;

import com.huuloc.hospital.entity.Employee;
import com.huuloc.hospital.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class ProfileController {
    private final EmployeeRepository employeeRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public ProfileController(EmployeeRepository employeeRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.employeeRepository = employeeRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @GetMapping(value = "/profile")
    public String profile(Model model, Authentication authentication) {
        model.addAttribute("employee", employeeRepository
                .findByUsername(authentication.getName())
                .orElseThrow(() -> new IllegalArgumentException("User not found"))
        );
        return "profile";
    }

    @PostMapping(value = "/profile/change-password")
    public ModelAndView updatePassword(Authentication authentication,
                                       @RequestParam("oldPassword") String oldPassword,
                                       @RequestParam("newPassword") String newPassword,
                                       @RequestParam("confirmPassword") String confirmPassword) {
        ModelAndView modelAndView = new ModelAndView(new RedirectView());
        Employee employeeExists = employeeRepository.findByUsername(authentication.getName())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        boolean hasError = false;
        if (!bCryptPasswordEncoder.matches(oldPassword, employeeExists.getPassword())) {
            modelAndView.addObject("errorMessage",
                    "Old password is incorrect");
            hasError = true;
        } else if (!newPassword.equals(confirmPassword)) {
            modelAndView.addObject("errorMessage",
                    "Confirm password is incorrect");
            hasError = true;
        } else if (newPassword.length() < 8) {
            modelAndView.addObject("errorMessage",
                    "Password must be at least 8 characters");
            hasError = true;
        }

        modelAndView.setViewName("profile");
        modelAndView.addObject("employee", employeeExists);
        if (!hasError) {
            employeeExists.setPassword(bCryptPasswordEncoder.encode(newPassword));
            employeeRepository.save(employeeExists);
            modelAndView.addObject("successMessage", "Password has been changed successfully");
        }

        return modelAndView;
    }
}
