package com.huuloc.hospital.service.impl;

import com.huuloc.hospital.entity.Patient;
import com.huuloc.hospital.repository.PatientRepository;
import com.huuloc.hospital.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatientServiceImpl implements PatientService {
    private final PatientRepository patientRepository;

    @Autowired
    public PatientServiceImpl(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    public Patient getPatientById(long id) {
        return patientRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Patient not found with id " + id)
        );
    }
}
