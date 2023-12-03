package com.huuloc.hospital.service;

import com.huuloc.hospital.entity.*;

import java.util.List;

public interface DoctorService {
    List<Patient> findAllPatient();
    Patient findPatientById(Long id);
    List<Drug> findAllDrug();
    Drug findDrugById(Long id);
    double getTotalPrice(List<PrescriptionItem> prescriptionItems);

    List<Prescription> findAllPrescription(
            Long patientId,
            Long doctorId);
    void addPrescription(Prescription prescription);
    Prescription findPrescriptionById(Long id);
}
