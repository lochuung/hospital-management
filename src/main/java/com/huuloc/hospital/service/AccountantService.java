package com.huuloc.hospital.service;


import com.huuloc.hospital.entity.Prescription;

import java.util.List;

public interface AccountantService {
    Double getMonthRevenue(int month, int year);
    List<Double> getRevenueStatisticByYear(int year);
    List<Prescription> getPrescriptionStatistic();
}
