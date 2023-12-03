package com.huuloc.hospital.service;

import com.huuloc.hospital.entity.*;
import com.huuloc.hospital.repository.PrescriptionRepository;
import com.huuloc.hospital.service.AccountantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountantServiceImpl implements AccountantService {
    private final PrescriptionRepository prescriptionRepository;

    @Autowired
    public AccountantServiceImpl(PrescriptionRepository prescriptionRepository) {
        this.prescriptionRepository = prescriptionRepository;
    }

    @Override
    public Double getMonthRevenue(int month, int year) {
        List<Prescription> prescriptions = prescriptionRepository
                .findAllByMonthAndYear(month, year);
        double revenue = 0;
        for (Prescription prescription : prescriptions) {
            List<PrescriptionItem> prescriptionItem = prescription.getPrescriptionItems();
            for (PrescriptionItem item : prescriptionItem) {
                revenue += item.getDrug().getPrice() * item.getQuantity();
            }

        }
        return revenue;
    }

    @Override
    public List<Double> getRevenueStatisticByYear(int year) {
        List<Double> revenues = new java.util.ArrayList<>();
        for (int i = 1; i <= 12; i++) {
            revenues.add(getMonthRevenue(i, year));
        }
        return revenues;
    }

    @Override
    public List<Prescription> getPrescriptionStatistic() {
        return prescriptionRepository.findAll();
    }
}
