package com.huuloc.hospital.entity;

import com.huuloc.hospital.util.Gender;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "employees")
@DiscriminatorValue(value = "Admin")
public class Admin extends Employee implements Serializable {
    public Admin() {
    }

    public Admin(Long id, String fullName, Date dob, Gender gender, String address,
                 String phone, String type, String email, Date joinDate, String username,
                 String password) {
        super(id, fullName, dob, gender, address, phone, type, email, joinDate,
                username, password);
    }
}
