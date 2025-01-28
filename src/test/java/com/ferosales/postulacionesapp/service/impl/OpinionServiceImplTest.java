package com.ferosales.postulacionesapp.service.impl;

import com.ferosales.postulacionesapp.entity.OpinionEntity;
import com.ferosales.postulacionesapp.repository.OpinionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class OpinionServiceImplTest {
    @Mock
    private OpinionRepository opinionRepository;

    @InjectMocks
    private OpinionServiceImpl opinionService;

    private OpinionEntity opinionEntity;

    @BeforeEach
    public void setUp() {
        opinionEntity = new OpinionEntity();
        opinionEntity.setId(1L);
        opinionEntity.setPersonalOpinion("Muy buena oferta");
    }

    @Test
    public void testSaveOpinion() {
        when(opinionRepository.save(any(OpinionEntity.class))).thenReturn(opinionEntity);

        OpinionEntity savedOpinion = opinionService.save(opinionEntity);

        verify(opinionRepository, times(1)).save(any(OpinionEntity.class));
        assertNotNull(savedOpinion);
        assertEquals(1L, savedOpinion.getId());
        assertEquals("Muy buena oferta", savedOpinion.getPersonalOpinion());
    }

}