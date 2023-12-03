package com.huuloc.hospital.entity;

import com.huuloc.hospital.util.Gender;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name = "patient")
public class Patient implements java.io.Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(unique = true, nullable = false)
	@Size(min = 9,max = 12, message = "Id card must be between 9 and 12 characters")
	private String idCard;
	@Column(nullable = false)
	@NotNull
	@Size(min = 5, max = 255, message = "Full name must be between 5 and 255 characters")
	private String fullName;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dob;
	@Enumerated(EnumType.STRING)
	private Gender gender;
	@Pattern(regexp = "((09|03|07|08|05)+([0-9]{8})\\b)",
			message = "Your phone number is invalid")
	private String phone;
	@Size(min = 2, max = 255, message = "Address must be between 2 and 255 characters")
	private String address;
	@Column(unique = true)
	@Size(max = 20, message = "Insurance number must be less than 20 characters")
	private String insuranceNumber;


	public Patient() {
	}

	public Patient(Long id, String idCard, String fullName, Date dob,
				   Gender gender, String phone, String address,
				   String insuranceNumber) {
		this.id = id;
		this.idCard = idCard;
		this.fullName = fullName;
		this.dob = dob;
		this.gender = gender;
		this.phone = phone;
		this.address = address;
		this.insuranceNumber = insuranceNumber;
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getInsuranceNumber() {
		return insuranceNumber;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public void setInsuranceNumber(String insuranceNumber) {
		this.insuranceNumber = insuranceNumber;
	}
}
