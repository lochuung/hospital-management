package com.huuloc.hospital.service;

import com.huuloc.hospital.entity.Prescription;

public interface PrescriptionService {
    Prescription getPrescriptionById(long id);
    long addPrescription(Prescription prescription);
    long updatePrescription(long id, Prescription prescription);
    void deletePrescription(long id);
}
