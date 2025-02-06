package com.ferosales.postulacionesapp.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentResponse {
    @JsonProperty("empresa")
    private String company;
    @JsonProperty("valoracion")
    private Double value;
    @JsonProperty("opiniones_glassdoor")
    private List<String> opinions;
    @JsonProperty("opinion_personal")
    private String comment;
}
