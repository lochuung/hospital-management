package com.huuloc.hospital.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
    @GetMapping(value = "/login")
    public String login(Authentication authentication) {
        if (authentication != null) {
            return "redirect:/";
        }
        return "login";
    }

    @GetMapping(value = "/")
    public String redirect(Authentication authentication) {
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
