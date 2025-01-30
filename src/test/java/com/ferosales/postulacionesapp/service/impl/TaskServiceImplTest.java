package com.ferosales.postulacionesapp.service.impl;

import com.ferosales.postulacionesapp.entity.ResponsibilityEntity;
import com.ferosales.postulacionesapp.entity.TaskEntity;
import com.ferosales.postulacionesapp.repository.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TaskServiceImplTest {

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskServiceImpl taskService;

    private TaskEntity task;

    @BeforeEach
    void setUp() {
        ResponsibilityEntity responsibility = new ResponsibilityEntity();
        responsibility.setId(1L);
        responsibility.setDescription("Test");
        task = new TaskEntity();
        task.setId(1L);
        task.setResponsibility(responsibility);
    }

    @Test
    void testViewTasks() {
        when(taskRepository.findAll()).thenReturn(Arrays.asList(task));
        List<TaskEntity> tasks = taskService.viewTasks();
        assertFalse(tasks.isEmpty());
        assertEquals(1, tasks.size());
        verify(taskRepository, times(1)).findAll();
    }

    @Test
    void testSaveTask() {
        taskService.saveTask(task);
        verify(taskRepository, times(1)).save(task);
    }

    @Test
    void testFindTaskByOfferId() {
        Long offerId = 1L;
        when(taskRepository.findByOfferId(offerId)).thenReturn(Arrays.asList(task));
        List<TaskEntity> tasks = taskService.findTaskByOfferId(offerId);
        assertFalse(tasks.isEmpty());
        assertEquals(1, tasks.size());
        verify(taskRepository, times(1)).findByOfferId(offerId);
    }

    @Test
    void testDeleteById() {
        Long id = 1L;
        taskService.deleteById(id);
        verify(taskRepository, times(1)).deleteById(id);
    }
}