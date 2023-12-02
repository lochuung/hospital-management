package com.huuloc.hospital.entity;

import javax.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "employee")
@DiscriminatorValue("Doctor")
public class Doctor extends Employee implements Serializable {
    public Doctor() {
    }
}
