package com.huuloc.hospital.repository;

import com.huuloc.hospital.entity.MedicalForm;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicalFormRepository extends JpaRepository<MedicalForm, Long> {
}
