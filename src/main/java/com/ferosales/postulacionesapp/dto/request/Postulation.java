package com.ferosales.postulacionesapp.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Postulation {
    @JsonProperty("titulo")
    private String title;
    @JsonProperty("empresa")
    private String company;
    @JsonProperty("descripcion")
    private String description;
    @JsonProperty("responsabilidades")
    private List<String> responsibilities;
    @JsonProperty("salario")
    private Double salary;
    @JsonProperty("beneficios")
    private String benefits;
    @JsonProperty("tiene_ingles")
    private Boolean english;
    @JsonProperty("fecha_publicacion")
    private Date datePublication;
    @JsonProperty("reclutador")
    private String recruiter;
    @JsonProperty("antiguedad")
    private int antique;
    @JsonProperty("tipo")
    private String type;
    @JsonProperty("cantidad_empleados")
    private int employees;
    @JsonProperty("opiniones")
    private List<String> opinions;
    @JsonProperty("validacion")
    private double validation;
    @JsonProperty("sueldo_opinion")
    private double salaryOpinion;
}
