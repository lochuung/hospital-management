package com.huuloc.hospital.controller;

import com.huuloc.hospital.entity.Prescription;
import com.huuloc.hospital.service.AccountantService;
import com.huuloc.hospital.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;

@Controller
@RequestMapping("/accountant")
public class AccountantController {
    private final AccountantService accountantService;
    private final DoctorService doctorService;

    @Autowired
    public AccountantController(AccountantService accountantService, DoctorService doctorService) {
        this.accountantService = accountantService;
        this.doctorService = doctorService;
    }

    @GetMapping(value = {
            "",
            "/",
    })
    public String index(Model model) {
        // get current year
        Date date = new Date();
        int year = date.getYear() + 1900;
        model.addAttribute("revenues", accountantService
                .getRevenueStatisticByYear(year));
        model.addAttribute("prescriptions",
                accountantService.getPrescriptionStatistic()
        );
        return "accountant/statistic";
    }

    @GetMapping("/prescription/{id}")
    public String prescription(Model model,
                               @PathVariable Long id) {
        Prescription prescription = doctorService.findPrescriptionById(id);
        model.addAttribute("prescription", prescription);
        model.addAttribute("totalPrice",
                doctorService.getTotalPrice(prescription.getPrescriptionItems()
                )
        );
        return "doctor/view-prescription";
    }
}
