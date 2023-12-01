package com.huuloc.hospital.service.impl;

import com.huuloc.hospital.entity.*;
import com.huuloc.hospital.repository.MedicalFormRepository;
import com.huuloc.hospital.repository.PrescriptionRepository;
import com.huuloc.hospital.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorServiceImpl implements DoctorService {
    private final PrescriptionRepository prescriptionRepository;
    private final MedicalFormRepository medicalFormRepository;

    @Autowired
    public DoctorServiceImpl(PrescriptionRepository prescriptionRepository, MedicalFormRepository medicalFormRepository) {
        this.prescriptionRepository = prescriptionRepository;
        this.medicalFormRepository = medicalFormRepository;
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
    public List<Patient> getAllPatients(long doctorId) {
        return medicalFormRepository
                .findAllPatientsByDoctor(doctorId);
    }

    @Override
    public List<Patient> getPatientsNotExamined(long doctorId) {
        return medicalFormRepository
                .getPatientsNotExaminedByDoctor(doctorId);
    }
}
