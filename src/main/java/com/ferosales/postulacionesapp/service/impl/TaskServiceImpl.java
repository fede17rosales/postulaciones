package com.ferosales.postulacionesapp.service.impl;

import com.ferosales.postulacionesapp.entity.TaskEntity;
import com.ferosales.postulacionesapp.repository.TaskRepository;
import com.ferosales.postulacionesapp.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Override
    public List<TaskEntity> viewTasks() {
        return (List<TaskEntity>) taskRepository.findAll();
    }

    @Override
    public void saveTask(TaskEntity task) {
        taskRepository.save(task);
    }

    @Override
    public List<TaskEntity> findTaskByOfferId(Long id) {
        return taskRepository.findByOfferId(id);
    }

    @Override
    public void deleteById(Long id) {
        taskRepository.deleteById(id);
    }
}
