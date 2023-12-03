package com.huuloc.hospital.repository;

import com.huuloc.hospital.entity.Doctor;
import com.huuloc.hospital.entity.Patient;
import com.huuloc.hospital.entity.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PrescriptionRepository extends JpaRepository<Prescription, Long> {
    List<Prescription> findAllByPatientAndDoctor(Patient patient, Doctor doctor);
    @Query(value = "SELECT * FROM prescription WHERE MONTH(date) = ?1 AND YEAR(date) = ?2",
            nativeQuery = true)
    List<Prescription> findAllByMonthAndYear(int month, int year);
}