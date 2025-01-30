package com.ferosales.postulacionesapp.entity;

import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
@Table(name = "offer")
public class OfferEntity {
    @Id
    @Column(name="ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @Lob
    @Column(name="description", length=512)
    private String description;
    private String recruiter;
    private Double salary;
    @Lob
    @Column(name="benefits", length=512)
    private String benefits;
    private Boolean english;
    private String status;
}
