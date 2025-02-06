package com.ferosales.postulacionesapp.repository;

import com.ferosales.postulacionesapp.entity.CompanyEntity;
import org.springframework.data.repository.CrudRepository;

public interface CompanyRepository extends CrudRepository<CompanyEntity, Long> {

    CompanyEntity findByName(String name);
}
