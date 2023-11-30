package com.huuloc.hospital.entity;

import javax.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "drug_stock")
public class DrugStock implements Serializable {
    @Id
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL,
            targetEntity = Drug.class)
    private Drug drug;
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL,
            targetEntity = Pharmacist.class)
    private Pharmacist pharmacist;
    private int quantity;

    public DrugStock() {
    }

    public DrugStock(Drug drug, int quantity) {
        this.drug = drug;
        this.quantity = quantity;
    }

    public Drug getDrug() {
        return drug;
    }

    public void setDrug(Drug drug) {
        this.drug = drug;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Pharmacist getPharmacist() {
        return pharmacist;
    }

    public void setPharmacist(Pharmacist pharmacist) {
        this.pharmacist = pharmacist;
    }
}
