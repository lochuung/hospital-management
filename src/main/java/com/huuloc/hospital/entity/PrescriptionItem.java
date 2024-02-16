package com.huuloc.hospital.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "prescription_items")
public class PrescriptionItem implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "prescription_item_id")
    private Long id;
    @ManyToOne(targetEntity = Drug.class,
            fetch = FetchType.EAGER)
    @JoinColumn(name = "drug_id")
    private Drug drug;
    @ManyToOne(targetEntity = Prescription.class,
            fetch = FetchType.LAZY, cascade =
            {CascadeType.MERGE, CascadeType.PERSIST,
            CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name = "prescription_id")
    private Prescription prescription;
    private int quantity;
}
