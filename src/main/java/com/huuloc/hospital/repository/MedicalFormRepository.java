package com.huuloc.hospital.repository;

import com.huuloc.hospital.entity.MedicalForm;
import com.huuloc.hospital.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MedicalFormRepository extends JpaRepository<MedicalForm, Long> {
    List<Patient> findAllPatientsByDoctor(@Param("doctorId")
                                  long doctorId);
    List<Patient> getPatientsNotExaminedByDoctor(@Param("doctorId")
                                                 long doctorId);
}
