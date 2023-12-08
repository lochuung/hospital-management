package com.huuloc.hospital.service.impl;

import com.huuloc.hospital.entity.Drug;
import com.huuloc.hospital.repository.DrugRepository;
import com.huuloc.hospital.service.PharmacistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PharmacistServiceImpl implements PharmacistService {
    private final DrugRepository drugRepository;

    @Autowired
    public PharmacistServiceImpl(DrugRepository drugRepository) {
        this.drugRepository = drugRepository;
    }

    @Override
    public List<Drug> getAllDrug() {
        return drugRepository.findAll();
    }

    @Override
    public Drug getDrugById(long id) {
        return drugRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid drug Id:" + id));
    }

    @Override
    public void addDrug(Drug drug) {
        drugRepository.save(drug);
    }

    @Override
    public void updateDrug(long id, Drug drug) {
        Drug drugUpdate = drugRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid drug Id:" + id));
        drugUpdate.setName(drug.getName());
        drugUpdate.setPrice(drug.getPrice());
        drugUpdate.setPharmacist(drug.getPharmacist());

        drugRepository.save(drugUpdate);
    }

    @Override
    public void deleteDrug(long id) {
        drugRepository.deleteById(id);
    }
}
