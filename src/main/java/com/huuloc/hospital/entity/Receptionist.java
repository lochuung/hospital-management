package com.huuloc.hospital.entity;

import com.huuloc.hospital.util.Gender;
import lombok.*;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

import java.io.Serializable;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "employees")
@DiscriminatorValue("Receptionist")
public class Receptionist extends Employee implements Serializable {
}
