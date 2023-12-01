package com.huuloc.hospital.service;

import com.huuloc.hospital.entity.Drug;
import com.huuloc.hospital.entity.Pharmacist;

import java.util.List;

public interface PharmacistService {
    long addDrug(Drug drug);
    void deleteDrug(long id);
    long updateDrug(long id, Drug drug);
    List<Drug> getAllDrugs();
}
