package com.ferosales.postulacionesapp.service;

import com.ferosales.postulacionesapp.entity.PublicationEntity;

import java.util.List;
import java.util.Optional;

public interface PublicationService {

    List<PublicationEntity> viewPublications();

    Optional<PublicationEntity> getPublication(Long id);

    void savePublication(PublicationEntity publication);

    void deletePublication(Long id);

}
