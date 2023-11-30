package com.huuloc.hospital.entity;

import javax.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "prescription_item")
public class PrescriptionItem implements Serializable {
    @Id
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL,
            targetEntity = Prescription.class)
    private Prescription prescription;
    @Id
    @OneToOne
    private DrugStock drugStock;
    private Integer quantity;
    private String note;

    public PrescriptionItem() {
    }

    public Prescription getPrescription() {
        return prescription;
    }

    public void setPrescription(Prescription prescription) {
        this.prescription = prescription;
    }

    public DrugStock getDrugStock() {
        return drugStock;
    }

    public void setDrugStock(DrugStock drugStock) {
        this.drugStock = drugStock;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
