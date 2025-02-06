package com.ferosales.postulacionesapp.repository;

import com.ferosales.postulacionesapp.entity.PublicationEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PublicationRepository extends CrudRepository<PublicationEntity, Long> {
    List<PublicationEntity> findAllByOrderByIdDesc();
}
