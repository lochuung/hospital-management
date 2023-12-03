package com.huuloc.hospital.util.mapper;

import com.huuloc.hospital.entity.Prescription;
import com.huuloc.hospital.util.dto.PrescriptionDTO;

public class PrescriptionMapper {
    public static Prescription toPrescription(PrescriptionDTO prescriptionDTO) {
        Prescription prescription = new Prescription();
        prescription.setId(prescriptionDTO.getId());
        prescription.setDiseaseName(prescriptionDTO.getDiseaseName());
        prescription.setNote(prescriptionDTO.getNote());
        return prescription;
    }
}
