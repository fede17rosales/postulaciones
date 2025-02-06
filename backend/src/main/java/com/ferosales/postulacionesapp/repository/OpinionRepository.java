package com.ferosales.postulacionesapp.repository;

import com.ferosales.postulacionesapp.entity.OpinionEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OpinionRepository extends CrudRepository<OpinionEntity,Long> {

    List<OpinionEntity> findByCompanyId(Long id);

    List<OpinionEntity> findAllByOrderByIdDesc();
}
