package com.huuloc.hospital.service;

import com.huuloc.hospital.entity.Doctor;
import com.huuloc.hospital.entity.MedicalForm;
import com.huuloc.hospital.entity.Patient;
import com.huuloc.hospital.entity.Receptionist;

import java.util.Date;
import java.util.List;

public interface ReceptionistService {
    long addPatient(Patient patient);
    boolean updatePatient(long id, Patient patient);
    void deletePatient(long id);
    List<Patient> getAllPatients();
    Patient getPatientById(long id);
    boolean checkPatientExists(Patient patient);
}
