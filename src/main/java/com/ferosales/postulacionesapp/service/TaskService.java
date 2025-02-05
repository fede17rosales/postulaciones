package com.ferosales.postulacionesapp.service;

import com.ferosales.postulacionesapp.entity.TaskEntity;

import java.util.List;

public interface TaskService {

    List<TaskEntity> viewTasks();
    void saveTask(TaskEntity task);

    List<TaskEntity> findTaskByOfferId(Long id);
    void deleteById(Long id);
}
