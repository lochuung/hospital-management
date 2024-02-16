package com.huuloc.hospital.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "drugs")
public class Drug implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @NotNull(message = "Price is required")
    private Double price;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "pharmacist_id")
    private Pharmacist pharmacist;

    @OneToMany(targetEntity = PrescriptionItem.class,
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL, mappedBy = "drug")
    private List<PrescriptionItem> prescriptionItem;
}
