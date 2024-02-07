package com.huuloc.hospital.entity;

import com.huuloc.hospital.util.Gender;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "employees")
@DiscriminatorValue("Pharmacist")
public class Pharmacist extends Employee {
    public Pharmacist() {
    }

    public Pharmacist(Long id, String fullName, Date dob, Gender gender, String address,
                      String phone, String type, String email, Date joinDate, String username,
                      String password) {
        super(id, fullName, dob, gender, address, phone, type, email, joinDate,
                username, password);
    }

}
