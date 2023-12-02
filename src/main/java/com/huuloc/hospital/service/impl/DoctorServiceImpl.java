package com.huuloc.hospital.service.impl;

import com.huuloc.hospital.entity.*;
import com.huuloc.hospital.repository.*;
import com.huuloc.hospital.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class DoctorServiceImpl implements DoctorService {
    @Autowired
    private PrescriptionRepository prescriptionRepository;
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private DrugRepository drugRepository;

    @Override
    public List<Patient> findAllPatient() {
        return patientRepository.findAll();
    }

    @Override
    public Patient findPatientById(Long id) {
        return patientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Patient not found"));
    }

    @Override
    public List<Drug> findAllDrug() {
        return drugRepository.findAll();
    }

    @Override
    public Drug findDrugById(Long id) {
        return drugRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Drug not found"));
    }

    @Override
    public List<Prescription> findAllPrescription(Long patientId, Long doctorId) {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new RuntimeException("Patient not found"));
        Doctor doctor = (Doctor) employeeRepository.findById(doctorId).
                orElseThrow(() -> new RuntimeException("Doctor not found"));

        return prescriptionRepository.findAllByPatientAndDoctor(patient, doctor);
    }

    @Override
    public long addPrescription(Prescription prescription) {
        return prescriptionRepository.save(prescription).getId();
    }

    @Override
    public Prescription findPrescriptionById(Long id) {
        return prescriptionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Prescription not found"));
    }

    @Override
    public long updatePrescription(Prescription prescription) {
        return prescriptionRepository.save(prescription).getId();
    }

    @Override
    public void deletePrescription(Long id) {
        prescriptionRepository.deleteById(id);
    }
}
