package com.huuloc.hospital.service.impl;

import com.huuloc.hospital.entity.Doctor;
import com.huuloc.hospital.entity.Patient;
import com.huuloc.hospital.entity.Receptionist;
import com.huuloc.hospital.repository.PatientRepository;
import com.huuloc.hospital.service.ReceptionistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ReceptionistServiceImp implements ReceptionistService {
    @Autowired
    private PatientRepository patientRepository;

    @Override
    public long addPatient(Patient patient) {
        patientRepository.save(patient);
        return patient.getId();
    }

    @Override
    public boolean updatePatient(long id, Patient patient) {
        Patient oldPatient = patientRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid patient Id:" + id));

        oldPatient.setFullName(patient.getFullName());
        oldPatient.setAddress(patient.getAddress());
        oldPatient.setPhone(patient.getPhone());
        oldPatient.setGender(patient.getGender());
        oldPatient.setDob(patient.getDob());
        oldPatient.setIdCard(patient.getIdCard());
        oldPatient.setInsuranceNumber(patient.getInsuranceNumber());

        patientRepository.save(oldPatient);
        return true;
    }

    @Override
    public void deletePatient(long id) {
        patientRepository.deleteById(id);
    }

    @Override
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    @Override
    public Patient getPatientById(long id) {
        return patientRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid patient Id:" + id));
    }

    @Override
    public boolean checkPatientExists(Patient patient) {
        return patientRepository.findByIdCardOrId(patient.getIdCard(), patient.getId())
                .isPresent();
    }
}
