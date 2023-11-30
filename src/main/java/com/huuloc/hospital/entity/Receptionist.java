package com.huuloc.hospital.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

import java.io.Serializable;

@Entity
@Table(name = "employee")
@DiscriminatorValue("Receptionist")
public class Receptionist extends Employee implements Serializable {
    public Receptionist() {
    }
}
