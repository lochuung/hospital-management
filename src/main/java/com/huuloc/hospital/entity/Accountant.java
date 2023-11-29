package com.huuloc.hospital.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.io.Serializable;

@Entity
@Table(name = "employee")
@DiscriminatorValue("Accountant")
public class Accountant extends Employee implements Serializable {
    public Accountant() {
    }
}
