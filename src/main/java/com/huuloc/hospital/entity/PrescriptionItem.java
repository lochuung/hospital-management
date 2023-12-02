package com.huuloc.hospital.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "prescription_item")
public class PrescriptionItem  implements Serializable {
    @Id
    @OneToOne(targetEntity = Drug.class,
            fetch = FetchType.EAGER)
    private Drug drug;
    @Id
    @ManyToOne(targetEntity = Prescription.class,
    fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Prescription prescription;
    private int quantity;

    public PrescriptionItem() {
    }

    public Drug getDrug() {
        return drug;
    }

    public void setDrug(Drug drug) {
        this.drug = drug;
    }

    public Prescription getPrescription() {
        return prescription;
    }

    public void setPrescription(Prescription prescription) {
        this.prescription = prescription;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
