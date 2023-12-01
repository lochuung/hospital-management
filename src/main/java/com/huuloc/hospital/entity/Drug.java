package com.huuloc.hospital.entity;

import javax.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "drug")
public class Drug implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Double price;
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL,
            targetEntity = Pharmacist.class)
    private Pharmacist pharmacist;
    private int quantity;
    public Drug() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Pharmacist getPharmacist() {
        return pharmacist;
    }

    public void setPharmacist(Pharmacist pharmacist) {
        this.pharmacist = pharmacist;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
