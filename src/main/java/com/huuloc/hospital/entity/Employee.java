package com.huuloc.hospital.entity;

import com.huuloc.hospital.util.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "employees")
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
}