package com.huuloc.hospital.service;

import com.huuloc.hospital.entity.Drug;

import java.util.List;

public interface PharmacistService {
    List<Drug> getAllDrug();
    Drug getDrugById(long id);

    void addDrug(Drug drug);

    void updateDrug(long id, Drug drug);

    void deleteDrug(long id);

}
