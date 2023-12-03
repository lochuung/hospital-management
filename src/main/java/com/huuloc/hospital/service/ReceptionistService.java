package com.huuloc.hospital.service;

import com.huuloc.hospital.entity.Patient;

import java.util.List;

public interface ReceptionistService {
    void addPatient(Patient patient);
    void updatePatient(long id, Patient patient);
    void deletePatient(long id);
    List<Patient> getAllPatients();
    Patient getPatientById(long id);
    boolean checkPatientExists(Patient patient);
}
