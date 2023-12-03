package com.huuloc.hospital.controller;

import com.huuloc.hospital.entity.Drug;
import com.huuloc.hospital.entity.Pharmacist;
import com.huuloc.hospital.service.EmployeeService;
import com.huuloc.hospital.service.PharmacistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(value = {
        "/pharmacist"
})
public class PharmacistController {
    private final PharmacistService pharmacistService;
    private final EmployeeService employeeService;

    @Autowired
    public PharmacistController(PharmacistService pharmacistService, EmployeeService employeeService) {
        this.pharmacistService = pharmacistService;
        this.employeeService = employeeService;
    }

    @GetMapping(value = {
            "", "/", "/drugs"
    })
    public String index(Model model) {
        List<Drug> drugs = pharmacistService.getAllDrug();
        model.addAttribute("drugs", drugs);
        return "pharmacist/drugs";
    }

    @GetMapping("/drugs/new")
    public String newDrug(Model model) {
        Drug drug = new Drug();
        model.addAttribute("drug", drug);
        return "pharmacist/new-drug";
    }

    @PostMapping("/drugs/save")
    public String saveDrug(@Valid Drug drug,
                           Model model, Authentication authentication) {
        drug.setPharmacist(
                (Pharmacist) employeeService.findByUsername(
                        authentication.getName()
                )
        );
        pharmacistService.addDrug(drug);
        model.addAttribute("successMessage",
                "Drug has been added successfully");
        return "redirect:/pharmacist/drugs/new";
    }

    @GetMapping("/drugs/edit/{id}")
    public String editDrug(@PathVariable("id") Long id,
                           Model model) {
        Drug drug = pharmacistService.getDrugById(id);
        model.addAttribute("drug", drug);
        return "pharmacist/edit-drug";
    }

    @PostMapping("/drugs/update/{id}")
    public String updateDrug(Drug drug,
                             @PathVariable("id") Long id,
                             Model model) {
        pharmacistService.updateDrug(id, drug);
        model.addAttribute("successMessage",
                "Drug has been updated successfully");
        model.addAttribute("drugs", pharmacistService.getAllDrug());
        return "/pharmacist/drugs";
    }

    @GetMapping("/drugs/delete/{id}")
    public String deleteDrug(@PathVariable("id") Long id,
                             Model model) {
        pharmacistService.deleteDrug(id);
        model.addAttribute("successMessage",
                "Drug has been deleted successfully");
        model.addAttribute("drugs", pharmacistService.getAllDrug());
        return "/pharmacist/drugs";
    }
}
