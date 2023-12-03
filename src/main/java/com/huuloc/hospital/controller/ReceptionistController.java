package com.huuloc.hospital.controller;

import com.huuloc.hospital.entity.Employee;
import com.huuloc.hospital.entity.Patient;
import com.huuloc.hospital.service.ReceptionistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;
import java.sql.Date;

@Controller
@RequestMapping("/receptionist")
public class ReceptionistController {
    private final ReceptionistService receptionistService;

    @Autowired
    public ReceptionistController(ReceptionistService receptionistService) {
        this.receptionistService = receptionistService;
    }

    @GetMapping(value = {"/", "", "/patients"})
    public String index(Model model) {
        model.addAttribute("patients", receptionistService
                .getAllPatients());
        return "receptionist/patients";
    }

    @GetMapping(value = "/patients/preview/{id}")
    public String previewPatient(Model model,
                                 @PathVariable("id") Long id) {
        try {
            model.addAttribute("patient", receptionistService
                    .getPatientById(id));
        } catch (IllegalArgumentException e) {
            model.addAttribute("errorMessage", e.getMessage());
        }
        return "receptionist/view-patient";
    }

    @GetMapping(value = "/patients/new")
    public String newPatient(Model model) {
        model.addAttribute("patient",
                new Patient());
        return "receptionist/new-patient";
    }

    @PostMapping(value = "/patients/save")
    public ModelAndView savePatient(@Valid Patient patient,
                                    BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView(new RedirectView());
        if (receptionistService.checkPatientExists(patient)) {
            bindingResult.rejectValue("idCard", "error.patient",
                    "There is already a patient registered with " +
                            "the ID Card provided");
        }

        modelAndView.setViewName("receptionist/new-patient");
        if (bindingResult.hasErrors()) {
            modelAndView.addObject("errorMessage",
                    bindingResult.getFieldError()
                            .getDefaultMessage());
        } else {
            receptionistService.addPatient(patient);
            modelAndView.addObject("successMessage",
                    "Patient has been added successfully");
        }
        return modelAndView;
    }

    @GetMapping(value = "/patients/edit/{id}")
    public String editEmployee(Model model, @PathVariable Long id) {
        try {
            model.addAttribute("patient", receptionistService
                    .getPatientById(id));
        } catch (IllegalArgumentException e) {
            model.addAttribute("errorMessage", e.getMessage());
        }

        return "receptionist/edit-patient";
    }

    @PostMapping(value = "/patients/update/{id}")
    public ModelAndView updateEmployee(@PathVariable Long id,
                                       @Valid Patient patient,
                                       BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView(new RedirectView());
        modelAndView.setViewName("receptionist/edit-patient");

        if (!receptionistService.checkPatientExists(patient)) {
            bindingResult.rejectValue("idCard", "error.patient",
                    "There is no patient registered with the id provided");
        }

        if (bindingResult.hasErrors()) {
            modelAndView.addObject("errorMessage",
                    bindingResult.getFieldError()
                            .getDefaultMessage());
        } else {
            receptionistService.updatePatient(id, patient);
            modelAndView.addObject("successMessage",
                    "Patient has been updated successfully");
        }
        return modelAndView;
    }

    @GetMapping(value = "/patients/delete/{id}")
    public ModelAndView deletePatient(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView(new RedirectView());
        try {
            receptionistService.deletePatient(id);
        } catch (IllegalArgumentException e) {
            modelAndView.addObject("errorMessage", e.getMessage());
        }
        modelAndView.addObject("successMessage",
                "Patient has been deleted successfully");
        modelAndView.addObject("patients", receptionistService
                .getAllPatients());
        modelAndView.setViewName("receptionist/patients");
        return modelAndView;
    }
}
