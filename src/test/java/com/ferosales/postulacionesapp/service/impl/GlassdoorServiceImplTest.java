package com.ferosales.postulacionesapp.service.impl;

import com.ferosales.postulacionesapp.entity.GlassdoorEntity;
import com.ferosales.postulacionesapp.repository.GlassdoorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

@SpringBootTest
class GlassdoorServiceImplTest {

    @Mock
    private GlassdoorRepository repository;

    @InjectMocks
    private GlassdoorServiceImpl glassdoorService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveGlassdoor() {
        // Arrange
        GlassdoorEntity glassdoor = new GlassdoorEntity();
        glassdoor.setValue(4.2);
        glassdoor.setSalary(60000);

        when(repository.save(glassdoor)).thenReturn(glassdoor);

        // Act
        GlassdoorEntity result = glassdoorService.saveGlassdoor(glassdoor);

        // Assert
        verify(repository, times(1)).save(glassdoor);
        assertThat(result).isNotNull();
        assertThat(result.getValue()).isEqualTo(4.2);
        assertThat(result.getSalary()).isEqualTo(60000);
    }

    @Test
    void testDeleteGlassdoorById() {
        // Arrange
        Long glassdoorId = 1L;
        doNothing().when(repository).deleteById(glassdoorId);

        // Act
        glassdoorService.deleteGlassdorById(glassdoorId);

        // Assert
        verify(repository, times(1)).deleteById(glassdoorId);
    }
}