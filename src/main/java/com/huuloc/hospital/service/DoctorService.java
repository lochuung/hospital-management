package com.huuloc.hospital.service;

import com.huuloc.hospital.entity.*;

import java.util.List;

public interface DoctorService {
    List<Patient> findAllPatient();
    Patient findPatientById(Long id);
    List<Drug> findAllDrug();
    Drug findDrugById(Long id);

    List<Prescription> findAllPrescription(
            Long patientId,
            Long doctorId);
    long addPrescription(Prescription prescription);
    Prescription findPrescriptionById(Long id);
    long updatePrescription(Prescription prescription);
    void deletePrescription(Long id);
}
