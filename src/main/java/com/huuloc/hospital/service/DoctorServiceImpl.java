package com.huuloc.hospital.service;

import com.huuloc.hospital.entity.*;
import com.huuloc.hospital.repository.*;
import com.huuloc.hospital.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorServiceImpl implements DoctorService {
    private final PrescriptionRepository prescriptionRepository;
    private final PatientRepository patientRepository;
    private final EmployeeRepository employeeRepository;
    private final DrugRepository drugRepository;

    @Autowired
    public DoctorServiceImpl(PrescriptionRepository prescriptionRepository, PatientRepository patientRepository, EmployeeRepository employeeRepository, DrugRepository drugRepository) {
        this.prescriptionRepository = prescriptionRepository;
        this.patientRepository = patientRepository;
        this.employeeRepository = employeeRepository;
        this.drugRepository = drugRepository;
    }

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
    public double getTotalPrice(List<PrescriptionItem> prescriptionItems) {
        double totalPrice = 0;
        for (PrescriptionItem prescriptionItem : prescriptionItems) {
            totalPrice += prescriptionItem.getDrug().getPrice() *
                    prescriptionItem.getQuantity();
        }
        return totalPrice;
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
    public void addPrescription(Prescription prescription) {
        prescriptionRepository.save(prescription);
    }

    @Override
    public Prescription findPrescriptionById(Long id) {
        return prescriptionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Prescription not found"));
    }
}
