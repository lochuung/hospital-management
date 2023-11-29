package com.huuloc.hospital.service.impl;

import com.huuloc.hospital.entity.*;
import com.huuloc.hospital.repository.PrescriptionRepository;
import com.huuloc.hospital.service.DoctorService;
import com.huuloc.hospital.service.MedicalFormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorServiceImpl implements DoctorService {
    private final PrescriptionRepository prescriptionRepository;
    private final MedicalFormService medicalFormService;

    @Autowired
    public DoctorServiceImpl(PrescriptionRepository prescriptionRepository,
                             MedicalFormService medicalFormService) {
        this.prescriptionRepository = prescriptionRepository;
        this.medicalFormService = medicalFormService;
    }


    @Override
    public long addPrescription(Disease disease, MedicalForm medicalForm,
                                List<PrescriptionItem> items, String note) {
        Prescription prescription = new Prescription();
        prescription.setDisease(disease);
        prescription.setMedicalForm(medicalForm);
        prescription.setPrescriptionItems(items);
        prescription.setNote(note);

        return prescriptionRepository.save(prescription).getId();
    }

    @Override
    public void deletePrescription(long id) {
        prescriptionRepository.deleteById(id);
    }

    @Override
    public long updatePrescription(long id,
                                   Disease disease,
                                   MedicalForm medicalForm,
                                   List<PrescriptionItem> items,
                                   String note) {
        Prescription prescription = prescriptionRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Prescription not found"));

        prescription.setDisease(disease);
        prescription.setPrescriptionItems(items);
        prescription.setNote(note);

        return prescriptionRepository.save(prescription).getId();
    }

    @Override
    public List<Patient> getAllPatients() {
        return null;
    }

    @Override
    public List<Patient> getPatientsNotExamined() {
        return null;
    }
}
