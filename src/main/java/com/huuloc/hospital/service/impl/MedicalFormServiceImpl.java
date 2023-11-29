package com.huuloc.hospital.service.impl;

import com.huuloc.hospital.entity.*;
import com.huuloc.hospital.repository.MedicalFormRepository;
import com.huuloc.hospital.service.EmployeeService;
import com.huuloc.hospital.service.MedicalFormService;
import com.huuloc.hospital.service.PatientService;
import com.huuloc.hospital.service.PrescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class MedicalFormServiceImpl implements MedicalFormService {
    private final MedicalFormRepository medicalFormRepository;
    private final EmployeeService employeeService;
    private final PatientService patientService;
    private final PrescriptionService prescriptionService;

    @Autowired
    public MedicalFormServiceImpl(MedicalFormRepository medicalFormRepository,
                                  EmployeeService employeeService,
                                  PatientService patientService,
                                  PrescriptionService prescriptionService) {
        this.medicalFormRepository = medicalFormRepository;
        this.employeeService = employeeService;
        this.patientService = patientService;
        this.prescriptionService = prescriptionService;
    }

    @Override
    public long addMedicalForm(long receptionistId,
                               long patientId,
                               long doctorId,
                               long prescriptionId,
                               Date examinationDate,
                               boolean isExamined) {
        Receptionist receptionist = (Receptionist) employeeService
                .getEmployeeById(receptionistId);
        Doctor doctor = (Doctor) employeeService
                .getEmployeeById(doctorId);
        Patient patient = patientService
                .getPatientById(patientId);
        Prescription prescription = null;
        if (prescriptionId > 0)
            prescription = prescriptionService
                    .getPrescriptionById(prescriptionId);

        MedicalForm medicalForm = new MedicalForm();
        medicalForm.setReceptionist(receptionist);
        medicalForm.setPatient(patient);
        medicalForm.setDoctor(doctor);
        medicalForm.setExamined(isExamined);
        medicalForm.setExaminationDate(examinationDate);
        medicalForm.setPrescription(prescription);

        return medicalFormRepository.save(medicalForm).getId();
    }

    @Override
    public long updateMedicalForm(long id, long receptionistId,
                                  long patientId, long doctorId,
                                  long prescriptionId, Date examinationDate,
                                  boolean isExamined) {
        Receptionist receptionist = (Receptionist) employeeService
                .getEmployeeById(receptionistId);
        Doctor doctor = (Doctor) employeeService
                .getEmployeeById(doctorId);
        Patient patient = patientService
                .getPatientById(patientId);
        Prescription prescription = null;
        if (prescriptionId > 0)
            prescription = prescriptionService
                    .getPrescriptionById(prescriptionId);

        MedicalForm medicalForm = medicalFormRepository.findById(id).orElseThrow(
                () -> new RuntimeException("MedicalForm not found with id " + id)
        );
        medicalForm.setReceptionist(receptionist);
        medicalForm.setPatient(patient);
        medicalForm.setDoctor(doctor);
        medicalForm.setExamined(isExamined);
        medicalForm.setExaminationDate(examinationDate);
        medicalForm.setPrescription(prescription);

        return medicalFormRepository.save(medicalForm).getId();
    }

    @Override
    public void deleteMedicalForm(long id) {

    }
}
