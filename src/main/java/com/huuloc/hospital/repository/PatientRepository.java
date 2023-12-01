package com.huuloc.hospital.repository;

import com.huuloc.hospital.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.validation.constraints.Size;
import java.util.Optional;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    Optional<Patient> findByIdCardOrId(@Size(min = 9, max = 12) String idCard, Long id);
}
