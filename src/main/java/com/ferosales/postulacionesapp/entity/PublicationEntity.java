package com.ferosales.postulacionesapp.entity;


import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@Entity
@Table(name = "publication")
public class PublicationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "offer_id")
    private OfferEntity offer;
    @ManyToOne
    @JoinColumn(name = "company_id")
    private CompanyEntity company;


    @Column(name = "date_publication")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date datePublication;
}
