package com.huuloc.hospital.entity;

import javax.persistence.*;

import java.util.Date;

@Entity
@Table(name = "medical_form")
public class MedicalForm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL,
            targetEntity = Receptionist.class)
    @JoinColumn(name = "receptionist_id")
    private Receptionist receptionist;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL,
            targetEntity = Patient.class)
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL,
            targetEntity = Doctor.class)
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL,
            targetEntity = Prescription.class)
    private Prescription prescription;
    @Temporal(TemporalType.DATE)
    private Date examinationDate;
    private boolean isExamined;

    public MedicalForm() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Prescription getPrescription() {
        return prescription;
    }

    public void setPrescription(Prescription prescription) {
        this.prescription = prescription;
    }

    public boolean isExamined() {
        return isExamined;
    }

    public void setExamined(boolean examined) {
        isExamined = examined;
    }

    public Receptionist getReceptionist() {
        return receptionist;
    }

    public void setReceptionist(Receptionist receptionist) {
        this.receptionist = receptionist;
    }

    public Date getExaminationDate() {
        return examinationDate;
    }

    public void setExaminationDate(Date examinationDate) {
        this.examinationDate = examinationDate;
    }
}
