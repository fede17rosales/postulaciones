package com.ferosales.postulacionesapp.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    @JsonProperty("compañia")
    private String company;
    @JsonProperty("comentario")
    private String comment;
}
