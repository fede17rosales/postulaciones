package com.ferosales.postulacionesapp.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "task")
public class TaskEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne()
    @JoinColumn(name = "offer_id")
    private OfferEntity offer;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "responsibility_id")
    private ResponsibilityEntity responsibility;
}
