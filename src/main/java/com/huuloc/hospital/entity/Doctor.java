package com.huuloc.hospital.entity;

import com.huuloc.hospital.util.Gender;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "employee")
@DiscriminatorValue("Doctor")
public class Doctor extends Employee implements Serializable {
    public Doctor() {
    }

    public Doctor(Long id, String fullName, Date dob, Gender gender,
                  String address, String phone, String type, String email, Date joinDate,
                  String username, String password) {
        super(id, fullName, dob, gender, address, phone, type, email, joinDate,
                username, password);
    }
}
