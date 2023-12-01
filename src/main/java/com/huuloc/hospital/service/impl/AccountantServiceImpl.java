package com.huuloc.hospital.service.impl;

import com.huuloc.hospital.entity.*;
import com.huuloc.hospital.repository.MedicalFormRepository;
import com.huuloc.hospital.service.AccountantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountantServiceImpl implements AccountantService {
    private final MedicalFormRepository medicalFormRepository;

    @Autowired
    public AccountantServiceImpl(MedicalFormRepository medicalFormRepository) {
        this.medicalFormRepository = medicalFormRepository;
    }

    @Override
    public Double findTotalPriceFromMedicalForm(long id) {
        MedicalForm form = medicalFormRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid form Id:" + id));

        Prescription prescription = form.getPrescription();
        List<PrescriptionItem> item = prescription.getPrescriptionItems();

        double totalPrice = 0;
        for (PrescriptionItem prescriptionItem : item) {
            Drug drug = prescriptionItem.getDrug();
            totalPrice += drug.getPrice() * prescriptionItem.getQuantity();
        }

        return totalPrice;
    }
}
