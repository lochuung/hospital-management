package com.huuloc.hospital.service;

import java.util.Date;

public interface MedicalFormService {
    long addMedicalForm(
            long receptionistId,
            long patientId,
            long doctorId,
            long prescriptionId,
            Date examinationDate,
            boolean isExamined
    );
    long updateMedicalForm(
            long id,
            long receptionistId,
            long patientId,
            long doctorId,
            long prescriptionId,
            Date examinationDate,
            boolean isExamined
    );
    void deleteMedicalForm(long id);
}
