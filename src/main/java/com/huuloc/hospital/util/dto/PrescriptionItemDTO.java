package com.huuloc.hospital.util.dto;

import java.io.Serializable;

public class PrescriptionItemDTO implements Serializable {
    private Long drugId;
    private int quantity;

    public Long getDrugId() {
        return drugId;
    }

    public void setDrugId(Long drugId) {
        this.drugId = drugId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
