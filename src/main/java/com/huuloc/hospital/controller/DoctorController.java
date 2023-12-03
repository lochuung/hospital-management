package com.huuloc.hospital.controller;

import com.huuloc.hospital.entity.*;
import com.huuloc.hospital.service.DoctorService;
import com.huuloc.hospital.service.EmployeeService;
import com.huuloc.hospital.util.dto.PrescriptionDTO;
import com.huuloc.hospital.util.dto.PrescriptionItemDTO;
import com.huuloc.hospital.util.mapper.PrescriptionMapper;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/doctor")
public class DoctorController {
    private final DoctorService doctorService;
    private final EmployeeService employeeService;

    public DoctorController(DoctorService doctorService, EmployeeService employeeService) {
        this.doctorService = doctorService;
        this.employeeService = employeeService;
    }

    @GetMapping(value = {
            "",
            "/",
            "/patients"
    })
    String index(Model model) {
        model.addAttribute("patients", doctorService.findAllPatient());
        return "doctor/patients";
    }

    @GetMapping("/prescriptions/{id}")
    String getPrescriptions(Model model,
                            @PathVariable("id") Long id,
                            Authentication authentication) {
        Doctor doctor = (Doctor) employeeService.findByUsername(
                authentication.getName()
        );
        model.addAttribute("prescriptions",
                doctorService.findAllPrescription(id,
                        doctor.getId()));
        model.addAttribute("patient",
                doctorService.findPatientById(id));
        return "doctor/prescriptions";
    }

    @GetMapping("/prescription/{id}")
    String getPrescription(Model model,
                           @PathVariable("id") Long id) {
        Prescription prescription = doctorService.findPrescriptionById(id);
        model.addAttribute("prescription", prescription);
        model.addAttribute("totalPrice",
                doctorService.getTotalPrice(prescription.getPrescriptionItems()
                )
        );
        return "doctor/view-prescription";
    }

    @GetMapping("/prescriptions/{id}/new")
    public String newPrescription(Model model,
                                  @PathVariable("id") Long id,
                                  Authentication authentication) {
        model.addAttribute("patient",
                doctorService.findPatientById(id));
        Prescription prescription = new Prescription();
        Doctor doctor = (Doctor) employeeService.findByUsername(
                authentication.getName()
        );
        Patient patient = doctorService.findPatientById(id);
        prescription.setPatient(patient);
        prescription.setDoctor(doctor);
        model.addAttribute("drugs", doctorService.findAllDrug());
        model.addAttribute("prescription", prescription);
        model.addAttribute("patient", patient);
        return "doctor/new-prescription";
    }

    @PostMapping("/prescriptions/{id}/save")
    @ResponseBody
    public String savePrescription(@PathVariable("id") Long id,
                                   Authentication authentication,
                                   @RequestBody PrescriptionDTO prescriptionDTO,
                                   HttpServletResponse response) {
        Prescription prescription = PrescriptionMapper
                .toPrescription(prescriptionDTO);
        prescription.setPatient(doctorService.findPatientById(id));
        prescription.setDoctor((Doctor) employeeService.findByUsername(
                authentication.getName()
        ));
        prescription.setDate(new Date());

        List<PrescriptionItem> prescriptionItems = getPrescriptionItems(prescriptionDTO);
        prescription.setPrescriptionItems(prescriptionItems);
        prescriptionItems.forEach(prescriptionItem -> prescriptionItem.setPrescription(prescription));

        try {
            doctorService.addPrescription(prescription);
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return "error";
        }
        return "success";
    }

    private List<PrescriptionItem> getPrescriptionItems(PrescriptionDTO
                                                                prescriptionDTO) {
        List<PrescriptionItem> prescriptionItems = new ArrayList<>();
        for (PrescriptionItemDTO prescriptionItemDTO : prescriptionDTO
                .getPrescriptionItems()) {
            prescriptionItems.add(getPrescriptionItem(prescriptionItemDTO));
        }
        return prescriptionItems;
    }

    private PrescriptionItem getPrescriptionItem(PrescriptionItemDTO
                                                         prescriptionItemDTO) {
        PrescriptionItem prescriptionItem = new PrescriptionItem();
        Drug drug = doctorService.findDrugById(prescriptionItemDTO.getDrugId());
        prescriptionItem.setDrug(drug);
        prescriptionItem.setQuantity(prescriptionItemDTO.getQuantity());
        return prescriptionItem;

    }
}
