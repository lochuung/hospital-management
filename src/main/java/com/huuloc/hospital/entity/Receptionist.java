package com.huuloc.hospital.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.io.Serializable;

@Entity
@Table(name = "employee")
@DiscriminatorValue("Receptionist")
public class Receptionist extends Employee implements Serializable {
    public Receptionist() {
    }
}
