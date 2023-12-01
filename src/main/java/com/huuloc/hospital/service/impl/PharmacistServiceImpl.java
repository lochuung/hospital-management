package com.huuloc.hospital.service.impl;

import com.huuloc.hospital.entity.Drug;
import com.huuloc.hospital.entity.Pharmacist;
import com.huuloc.hospital.repository.DrugRepository;
import com.huuloc.hospital.service.PharmacistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PharmacistServiceImpl implements PharmacistService {
    @Autowired
    private DrugRepository drugRepository;
    @Override
    public long addDrug(Drug drug) {
        drugRepository.save(drug);
        return drug.getId();
    }

    @Override
    public void deleteDrug(long id) {
        drugRepository.deleteById(id);
    }

    @Override
    public long updateDrug(long id, Drug drug) {
        drug.setId(id);
        drugRepository.save(drug);
        return drug.getId();
    }

    @Override
    public List<Drug> getAllDrugs() {
        return null;
    }
}
