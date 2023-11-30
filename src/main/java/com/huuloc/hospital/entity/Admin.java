package com.huuloc.hospital.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

import java.io.Serializable;

@Entity
@Table(name = "employee")
@DiscriminatorValue(value = "Admin")
public class Admin extends Employee implements Serializable {
    public Admin() {
    }
}
