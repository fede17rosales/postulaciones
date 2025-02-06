package com.ferosales.postulacionesapp.repository;

import com.ferosales.postulacionesapp.entity.TaskEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TaskRepository extends CrudRepository<TaskEntity, Long> {
    List<TaskEntity> findByOfferId(Long id);
}
