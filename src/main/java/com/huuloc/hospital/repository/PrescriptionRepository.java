package com.huuloc.hospital.repository;

import com.huuloc.hospital.entity.Doctor;
import com.huuloc.hospital.entity.Patient;
import com.huuloc.hospital.entity.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PrescriptionRepository extends JpaRepository<Prescription, Long> {
    List<Prescription> findAllByPatientAndDoctor(Patient patient, Doctor doctor);
}