package com.huuloc.hospital.entity;

import com.huuloc.hospital.util.Gender;
import lombok.*;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Entity
@DiscriminatorValue("Doctor")
public class Doctor extends Employee implements Serializable {
}
