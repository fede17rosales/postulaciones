package com.ferosales.postulacionesapp.service;

import com.ferosales.postulacionesapp.dto.request.Postulation;
import com.ferosales.postulacionesapp.dto.response.PostulationResponse;

import java.util.List;

public interface PostulationService {

    List<PostulationResponse> viewPostulations();
    void savePostulation(Postulation postulacion);

    void deletePostulation(Long id);

}
