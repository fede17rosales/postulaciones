package com.ferosales.postulacionesapp.dto.response;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostulationResponse {
    @JsonProperty("id")
    public Long id;
    @JsonProperty("empresa")
    public String company;
    @JsonProperty("titulo")
    public String title;
    @JsonProperty("descripcion")
    public String description;
    @JsonProperty("responsabilidades")
    public List<String> responsibilities;
    @JsonProperty("salario")
    public String salary;
    @JsonProperty("reclutador")
    public String recruiter;
    @JsonProperty("fecha_de_alta")
    public String date;
}
