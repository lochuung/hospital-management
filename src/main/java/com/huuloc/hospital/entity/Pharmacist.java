package com.huuloc.hospital.entity;

import com.huuloc.hospital.util.Gender;
import lombok.*;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Date;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@NoArgsConstructor
@AllArgsConstructor
@DiscriminatorValue("Pharmacist")
public class Pharmacist extends Employee {
    @OneToMany(mappedBy = "pharmacist")
    private List<Drug> drugs;
}
