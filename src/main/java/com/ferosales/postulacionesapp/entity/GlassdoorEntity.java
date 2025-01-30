package com.ferosales.postulacionesapp.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "glassdoor")
public class GlassdoorEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    @Column(length = 65555)
    private List<String> opinions;
    private double value;
    private double salary;

}
