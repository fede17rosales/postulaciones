package com.ferosales.postulacionesapp.service.impl;

import com.ferosales.postulacionesapp.entity.ResponsibilityEntity;
import com.ferosales.postulacionesapp.repository.ResponsibilityRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ResponsibilityServiceImplTest {
    @Mock
    private ResponsibilityRepository responsibilityRepository;

    @InjectMocks
    private ResponsibilityServiceImpl responsibilityService;

    private ResponsibilityEntity responsibility;

    @BeforeEach
    void setUp() {
        responsibility = new ResponsibilityEntity();
        responsibility.setId(1L);
        responsibility.setDescription("Test Responsibility");
    }

    @Test
    void testSaveResponsibility() {
        responsibilityService.saveResponsibility(responsibility);
        verify(responsibilityRepository, times(1)).save(responsibility);
    }

    @Test
    void testDeleteResponsibility() {
        Long id = 1L;
        responsibilityService.deleteResponsibility(id);
        verify(responsibilityRepository, times(1)).deleteById(id);
    }

}