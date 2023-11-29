package com.huuloc.hospital.service;

import com.huuloc.hospital.entity.*;

import java.util.List;

public interface DoctorService {
    long addPrescription(Disease disease,
                         MedicalForm medicalForm,
                         List<PrescriptionItem> items,
                         String note);
    void deletePrescription(long id);
    long updatePrescription(long id,
                            Disease disease,
                            MedicalForm medicalForm,
                            List<PrescriptionItem> items,
                            String note);

    List<Patient> getAllPatients();
    List<Patient> getPatientsNotExamined();
}
