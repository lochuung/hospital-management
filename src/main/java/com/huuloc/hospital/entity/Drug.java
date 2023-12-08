package com.huuloc.hospital.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "drug")
public class Drug implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @NotNull(message = "Price is required")
    private Double price;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "pharmacist_id")
    private Pharmacist pharmacist;
    public Drug() {
    }

    public Drug(Long id, String name,
                Double price, Pharmacist pharmacist) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.pharmacist = pharmacist;
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
}
