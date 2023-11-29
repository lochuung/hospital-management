package com.huuloc.hospital.service.impl;

import com.huuloc.hospital.entity.Prescription;
import com.huuloc.hospital.repository.PrescriptionRepository;
import com.huuloc.hospital.service.PrescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PrescriptionServiceImp implements PrescriptionService {
    private final PrescriptionRepository prescriptionRepository;

    @Autowired
    public PrescriptionServiceImp(PrescriptionRepository prescriptionRepository) {
        this.prescriptionRepository = prescriptionRepository;
    }

    @Override
    public Prescription getPrescriptionById(long id) {
        return prescriptionRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Prescription not found with id " + id)
        );
    }

    @Override
    public long addPrescription(Prescription prescription) {
        return prescriptionRepository.save(prescription).getId();
    }

    @Override
    public long updatePrescription(long id, Prescription prescription) {
        Prescription prescriptionUpdate = prescriptionRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Prescription not found with id " + id)
        );
        prescriptionUpdate.setPrescriptionItems(prescription.getPrescriptionItems());
        prescriptionUpdate.setMedicalForm(prescription.getMedicalForm());
        prescriptionUpdate.setDisease(prescription.getDisease());
        prescriptionUpdate.setNote(prescription.getNote());
        return prescriptionRepository.save(prescriptionUpdate).getId();
    }

    @Override
    public void deletePrescription(long id) {
        prescriptionRepository.deleteById(id);
    }
}
