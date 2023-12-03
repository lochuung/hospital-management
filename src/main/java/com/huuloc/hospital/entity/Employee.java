package com.huuloc.hospital.entity;

import com.huuloc.hospital.util.Gender;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "employee")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)
public class Employee implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    @NotNull
    @Size(min = 5, max = 255, message = "Full name must be between 5 and 255 characters")
    private String fullName;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dob;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private String address;
    @Pattern(regexp = "((09|03|07|08|05)+([0-9]{8})\\b)",
            message = "Your phone number is invalid")
    private String phone;
    @Column(updatable = false, insertable = false)
    private String type;
    @Column(unique = true, nullable = false)
    @Pattern(regexp = "^[A-Za-z0-9+_.-]+@(.+)$", message = "Email is invalid")
    private String email;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date joinDate;
    @Column(unique = true, nullable = false)
    @Size(max = 20, min = 3, message = "Username must be between 3 and 20 characters")
    private String username;
    @Size(min = 8, message = "Password must be at least 8 characters")
    private String password;

    public Employee() {
    }

    public Employee(Long id, String fullName,
                    Date dob, Gender gender, String address,
                    String phone, String type, String email, Date joinDate,
                    String username, String password) {
        this.id = id;
        this.fullName = fullName;
        this.dob = dob;
        this.gender = gender;
        this.address = address;
        this.phone = phone;
        this.type = type;
        this.email = email;
        this.joinDate = joinDate;
        this.username = username;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(Date joinDate) {
        this.joinDate = joinDate;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}